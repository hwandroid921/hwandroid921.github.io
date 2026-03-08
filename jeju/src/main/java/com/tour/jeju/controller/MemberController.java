package com.tour.jeju.controller;

import com.tour.jeju.dto.*;
import com.tour.jeju.service.MemberService;
import com.tour.jeju.service.TourGuideService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final TourGuideService tourGuideService;

    // 회원가입 페이지 로딩
    @GetMapping("/join")
    public String joinForm(Model model) {

        model.addAttribute("memberJoinRequest", new MemberJoinRequest());

        return "member/join";
    }

    // 회원가입 처리
    @PostMapping("/join")
    public String join(@Valid @ModelAttribute MemberJoinRequest request,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        // 비밀번호 일치 검사
        if (!request.isPasswordMatch()) {
            bindingResult.rejectValue("passwordConfirm", "passwordMismatch", "비밀번호가 일치하지 않습니다.");
        }

        if (bindingResult.hasErrors()) {

            return "member/join";
        }
        try {
            memberService.join(request);
            redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다. 로그인 해 주세요");

            return "redirect:/member/login";
        } catch (IllegalArgumentException e) {
            bindingResult.reject("joinFailed", e.getMessage());

            return "member/join";
        }
    }

    // 로그인 페이지 로딩
    @GetMapping("/login")
    public String loginForm(@RequestParam(name = "redirectURL", required = false) String redirectURL, Model model) {
        model.addAttribute("memberLoginRequest", new MemberLoginRequest());
        model.addAttribute("redirectURL", redirectURL);

        return "member/login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute MemberLoginRequest request,
                        BindingResult bindingResult,
                        @RequestParam(name = "redirectURL", required = false) String redirectURL,
                        HttpServletRequest httpServletRequest,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "member/login";
        }
        try {
            MemberResponse member = memberService.login(request);
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("loginMember", member);
            log.info("로그인 성공 - Username: {}, Session: {}", member.getUsername(), session.getId());
            redirectAttributes.addFlashAttribute("message", member.getName() + "님 환영합니다");
            if (redirectURL != null && !redirectURL.isEmpty()) {
                return "redirect:" + redirectURL;
            }
            return "redirect:/";
        }
        catch (IllegalArgumentException e) {
            bindingResult.reject("loginFailed", e.getMessage());
            return "member/login";
        }
    }

    // 로그 아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            log.info("로그아웃 완료");
        }
        redirectAttributes.addFlashAttribute("message", "로그아웃 되었습니다.");

        return "redirect:/";
    }

    // 회원정보 수정 폼 로드
    @GetMapping("/edit")
    public String editForm(HttpSession session, Model model) {
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        MemberResponse member = memberService.getMember(loginMember.getId());

        MemberUpdateRequest request = MemberUpdateRequest.builder()
                .name(member.getName())
                .build();
        model.addAttribute("memberUpdateRequest", request);
        return "member/edit";
    }

    // 회원 정보 수정 처리
    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute MemberUpdateRequest request,
                       BindingResult bindingResult,
                       HttpSession session,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "member/edit";
        }
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        try {
            // 회원 정보 수정
            memberService.updateMember(loginMember.getId(), request);
            // 세션 정보 갱신
            MemberResponse updateMember = memberService.getMember(loginMember.getId());
            session.setAttribute("loginMember", updateMember);
            redirectAttributes.addFlashAttribute("message", "회원 정보가 수정되었습니다.");
            return "redirect:/member/mypage";
        } catch (IllegalArgumentException e) {
            bindingResult.reject("updateFailed", e.getMessage());
            return "member/edit";
        }
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String mypage (
            HttpSession session,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {  //
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        MemberResponse member = memberService.getMember(loginMember.getId());
        Page<TourGuideResponse> myBoards = tourGuideService.getMyBoardList(loginMember.getId(), page, size);

        model.addAttribute("member",member);
        model.addAttribute("myBoards",myBoards);
        return "member/mypage";
    }


    // 회원 목록
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false) String searchType,
                       @RequestParam(required = false) String keyword,
                       Model model) {
        Page<MemberResponse> memberPage = memberService.getMemberList(page, size, searchType, keyword);
        model.addAttribute("memberPage", memberPage); // 담기

        // 전체 페이지 수
        int totalPages = memberPage.getTotalPages(); // Page, Pageable
        model.addAttribute("totalPages", totalPages);

        // 전체 회원 수
        long totalElements = memberPage.getTotalElements();
        model.addAttribute("totalElements", totalElements);

        // 현재 페이지 번호
        int currentPage = memberPage.getNumber();
        model.addAttribute("currentPage", currentPage);

        int pageBlockSize = 10;
        // 시작 페이지
        //
        int startPage = ((currentPage - 1) / pageBlockSize) * pageBlockSize + 1;        // 끝 페이지

        int endPage = Math.min((startPage + pageBlockSize - 1), totalPages);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        return "member/list";
    }
}

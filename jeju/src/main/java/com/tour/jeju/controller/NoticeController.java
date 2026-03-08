package com.tour.jeju.controller;

import com.tour.jeju.dto.MemberResponse;
import com.tour.jeju.dto.NoticeRequest;
import com.tour.jeju.dto.NoticeResponse;
import com.tour.jeju.service.NoticeService;
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
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/community/notice")
public class NoticeController {
    private final NoticeService noticeService;

    // 공지사항 목록
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false) String searchType,
                       @RequestParam(required = false) String keyword,
                       Model model) {

        Page<NoticeResponse> noticePage = noticeService.getNoticeList(page, size, searchType, keyword);
        model.addAttribute("noticeList", noticePage);

        // 전체 페이지 수
        int totalPages = noticePage.getTotalPages();
        model.addAttribute("totalPages", totalPages);

        // 전체 글 수
        long totalElements = noticePage.getTotalElements();
        model.addAttribute("totalElements", totalElements);

        // 현재 페이지 번호
        int currentPage = noticePage.getNumber();
        model.addAttribute("currentPage", currentPage);

        int pageBlockSize = 10;

        // 시작 페이지
        int startPage = ((currentPage - 1) / pageBlockSize) * pageBlockSize + 1;        // 끝 페이지

        int endPage = Math.min((startPage + pageBlockSize - 1), totalPages);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "community/notice/list";
    }

    // 게시글 상세보기
    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Long id,
                         HttpSession session,
                         Model model) {
        NoticeResponse notice = noticeService.getNotice(id);
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        boolean isWriter = false;
        if (loginMember != null) {  // 로그인한 사용자가 글 작성자인지
            isWriter = notice.getAuthorId().equals(loginMember.getId());
        }
        model.addAttribute("noticeDetail", notice);
        model.addAttribute("isWriter", isWriter);

        return "community/notice/detail";
    }

    // 게시글 등록 폼 로딩
    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("noticeRequest", new NoticeRequest());
        return "community/notice/write";
    }

    // 게시글 등록 처리
    @PostMapping("/write")
    public String write(@Valid @ModelAttribute NoticeRequest request,
                        BindingResult bindingResult,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "community/notice/write";
        }
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");

        try {
            Long noticeId = noticeService.insertNotice(request, loginMember.getId());
            redirectAttributes.addFlashAttribute("messages", "게시글이 등록되었습니다.");
            return "redirect:/community/notice/detail/"+noticeId;
        } catch (IllegalArgumentException e) {
            log.info("게시글 작성 실패", e);
            bindingResult.reject("writeFailed", "게시글 등록에 실패했습니다.");
            return "community/notice/write";
        }
    }

    // 게시글 수정 폼 로딩
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                           HttpSession session,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        try {
            NoticeResponse notice = noticeService.getNotice(id, loginMember.getId());
            NoticeRequest request = NoticeRequest.builder()
                    .title(notice.getTitle())
                    .content(notice.getContent())
                    .build();
            model.addAttribute("noticeRequest", request);
            model.addAttribute("noticeId", id);
            return "community/notice/edit";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/community/notice/detail/" + id;
        }

    }

    // 게시글 수정 처리
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @Valid @ModelAttribute NoticeRequest request,
                       BindingResult bindingResult,
                       HttpSession session,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("noticeId", id);
            return "community/notice/edit";
        }
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        try {
            noticeService.editNotice(id, request, loginMember.getId());
            redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");
            return "redirect:/community/notice/detail/"+id;
        } catch (IllegalArgumentException e) {
            bindingResult.reject("editFailed", e.getMessage());
            model.addAttribute("noticeId", id);
            return "community/notice/edit";
        }
    }

    // 게시글 삭제
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        try {
            noticeService.deleteNotice(id, loginMember.getId());
            redirectAttributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");
            return "redirect:/community/notice/list";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/community/notice/detail/"+id;
        }

    }
}

package com.tour.jeju.controller;

import com.tour.jeju.dto.MemberJoinRequest;
import com.tour.jeju.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

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
}

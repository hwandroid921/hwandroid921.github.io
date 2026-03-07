package com.tour.jeju.controller;

import com.tour.jeju.dto.AttractionRequest;
import com.tour.jeju.dto.MemberResponse;
import com.tour.jeju.service.AttractionService;
import com.tour.jeju.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/attraction")
public class AttractionController {
    private final AttractionService attractionService;
    private final ReviewService reviewService;

    // 관광지 상세 페이지
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id,
            Model model) {
        model.addAttribute("attraction", attractionService.getAttraction(id));
        return "attraction/detail";
    }

    // 관광지 등록 폼 로딩
    @GetMapping("/insert")
    public String insertForm(Model model) {
        model.addAttribute("attractionRequest", new AttractionRequest());
        return "attraction/insert";
    }

    // 관광지 등록 처리
    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute AttractionRequest request,
                         BindingResult bindingResult,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "attraction/insert";
        }
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        try {
            Long attractionId = attractionService.insertAttraction(request, loginMember.getId());
            redirectAttributes.addFlashAttribute("message", "게시글이 등록되었습니다.");
            return "redirect:/attraction/detail/" + attractionId;
        } catch (IllegalArgumentException e) {
            bindingResult.reject("insertFailed", "등록에 실패했습니다.");
            return "attraction/insert";
        }

    }

    // 관광지 수정 폼 로딩
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("edit", attractionService.getAttraction(id));
        return "attraction/edit";
    }
    // 관광지 수정 처리
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @ModelAttribute AttractionRequest request,
                       Model model) {

        attractionService.editAttraction(id, request);
        return "redirect:/attraction/detail/"+id;

    }


























//    @GetMapping("/edit/{id}")
//    public String editForm(@PathVariable("id") Long id,
//                           HttpSession session,
//                           RedirectAttributes redirectAttributes,
//                           Model model) {
//        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
//        try {
//            AttractionResponse attraction = attractionService.getAttraction(id, loginMember.getId());
//        }
//    }

    // 관광지 수정 처리
//    @PostMapping("/edit/{id}")
//    public String edit(@PathVariable("id") Long id,
//                       @Valid @ModelAttribute AttractionRequest request,
//                       BindingResult bindingResult,
//                       HttpSession session,
//                       RedirectAttributes redirectAttributes,
//                       Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute(())
//            return
//        }
//    }
    // 관광지 삭제
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        attractionService.deleteAttraction(id);
        return "redirect:/attraction/list";
    }
}

package com.tour.jeju.controller;

import com.tour.jeju.dto.AttractionRequest;
import com.tour.jeju.dto.AttractionResponse;
import com.tour.jeju.dto.MemberResponse;
import com.tour.jeju.service.AttractionService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/attraction")
public class AttractionController {
    private final AttractionService attractionService;

    // 관광지 상세 페이지
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("attraction", attractionService.getAttraction(id));
        model.addAttribute("attractionList", attractionService.getAttractionList());
        return "attraction/detail";
    }

    // 관광지 등록 폼 로딩
    @GetMapping("/insert")
    public String insertForm(Model model, HttpSession session) {

        if (session.getAttribute("loginMember") == null) {
            return "redirect:/member/login";
        }
        model.addAttribute("attractionRequest", new AttractionRequest());
        return "attraction/insert";
    }

    // 관광지 등록 처리
    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute AttractionRequest request,
                         BindingResult bindingResult,
                         HttpSession session,
                         RedirectAttributes redirectAttributes,
                         @RequestParam(value = "thumb",  required = false) MultipartFile thumb) {
        if (bindingResult.hasErrors()) {
            return "attraction/insert";
        }
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/member/login";
        }
        try {
            Long attractionId = attractionService.insertAttraction(request, loginMember.getId(), thumb);
            redirectAttributes.addFlashAttribute("message", "관광지이 등록되었습니다.");
            return "redirect:/attraction/detail/" + attractionId;

        } catch (Exception e) {
            bindingResult.reject("insertFailed", "등록에 실패했습니다.");
            return "attraction/insert";
        }

    }

    // 관광지 수정 폼 로딩
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("loginMember") == null) {
            return "redirect:/member/login";
        }
        AttractionResponse response = attractionService.getAttraction(id);
        AttractionRequest request   = AttractionRequest.toDto(response);

        model.addAttribute("attractionRequest", request);
        model.addAttribute("attractionId", id);
        model.addAttribute("attraction", response);

        return "attraction/edit";
    }

    // 관광지 수정 처리
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @Valid @ModelAttribute AttractionRequest request,
                       @RequestParam(value = "thumb",  required = false) MultipartFile thumb,
                       RedirectAttributes redirectAttributes,
                       BindingResult bindingResult,
                       HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "attraction/edit";
        }

        try {
            attractionService.editAttraction(id, request, thumb);
            redirectAttributes.addFlashAttribute("message", "관광지가 수정되었습니다.");
            return "redirect:/attraction/detail/" + id;
        } catch (Exception e) {
            bindingResult.reject("editFailed", "수정에 실패했습니다.");
            return "attraction/edit";
        }
    }

    // 관광지 삭제
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            attractionService.delete(id);
            redirectAttributes.addFlashAttribute("message", "관광지가 삭제되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "삭제에 실패했습니다.");
        }
        return "redirect:/attraction/list";
    }
}




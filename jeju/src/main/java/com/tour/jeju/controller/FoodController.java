package com.tour.jeju.controller;

import com.tour.jeju.dto.FoodRequest;
import com.tour.jeju.dto.FoodResponse;
import com.tour.jeju.dto.MemberResponse;
import com.tour.jeju.entity.Role;
import com.tour.jeju.service.FoodService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    // 음식 목록 → 첫 번째 음식으로 리다이렉트
    @GetMapping
    public String index(RedirectAttributes redirectAttributes) {
        List<FoodResponse> foods = foodService.getFoodList();
        if (foods.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "등록된 음식이 없습니다.");
            return "redirect:/";
        }
        return "redirect:/food/" + foods.get(0).getId();
    }

    // 음식 조회 (F-017)
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("food", foodService.getFood(id));
            foodService.getPrevFood(id).ifPresent(prev -> model.addAttribute("prevFood", prev));
            foodService.getNextFood(id).ifPresent(next -> model.addAttribute("nextFood", next));
            return "food/food";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/food";
        }
    }

    // 음식 등록 폼 (F-018)
    @GetMapping("/insert")
    public String insertForm(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        if (loginMember == null || loginMember.getRole() != Role.ADMIN) {
            redirectAttributes.addFlashAttribute("error", "관리자만 접근 가능합니다.");
            return "redirect:/member/login";
        }
        model.addAttribute("foodRequest", new FoodRequest());
        return "food/form";
    }

    // 음식 등록 처리 (F-018)
    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute FoodRequest request,
                         BindingResult bindingResult,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        if (loginMember == null || loginMember.getRole() != Role.ADMIN) {
            redirectAttributes.addFlashAttribute("error", "관리자만 접근 가능합니다.");
            return "redirect:/member/login";
        }
        if (bindingResult.hasErrors()) {
            return "food/form";
        }
        try {
            Long foodId = foodService.insertFood(request);
            redirectAttributes.addFlashAttribute("message", "음식이 등록되었습니다.");
            return "redirect:/food/" + foodId;
        } catch (IllegalArgumentException e) {
            log.info("음식 등록 실패", e);
            bindingResult.reject("insertFailed", "음식 등록에 실패했습니다.");
            return "food/form";
        }
    }

    // 음식 수정 폼 (F-019)
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id,
                           HttpSession session,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        if (loginMember == null || loginMember.getRole() != Role.ADMIN) {
            redirectAttributes.addFlashAttribute("error", "관리자만 접근 가능합니다.");
            return "redirect:/member/login";
        }
        try {
            FoodResponse food = foodService.getFood(id);
            FoodRequest request = FoodRequest.builder()
                    .name(food.getName())
                    .description(food.getDescription())
                    .imgUrl(food.getImgUrl())
                    .build();
            model.addAttribute("foodRequest", request);
            model.addAttribute("foodId", id);
            return "food/form";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/food/" + id;
        }
    }

    // 음식 수정 처리 (F-019)
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @Valid @ModelAttribute FoodRequest request,
                       BindingResult bindingResult,
                       HttpSession session,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        if (loginMember == null || loginMember.getRole() != Role.ADMIN) {
            redirectAttributes.addFlashAttribute("error", "관리자만 접근 가능합니다.");
            return "redirect:/member/login";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("foodId", id);
            return "food/form";
        }
        try {
            foodService.editFood(id, request);
            redirectAttributes.addFlashAttribute("message", "음식이 수정되었습니다.");
            return "redirect:/food/" + id;
        } catch (IllegalArgumentException e) {
            bindingResult.reject("editFailed", e.getMessage());
            model.addAttribute("foodId", id);
            return "food/form";
        }
    }

    // 음식 삭제 (F-020)
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        if (loginMember == null || loginMember.getRole() != Role.ADMIN) {
            redirectAttributes.addFlashAttribute("error", "관리자만 접근 가능합니다.");
            return "redirect:/member/login";
        }
        try {
            foodService.deleteFood(id);
            redirectAttributes.addFlashAttribute("message", "음식이 삭제되었습니다.");
            return "redirect:/food";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/food/" + id;
        }
    }
}

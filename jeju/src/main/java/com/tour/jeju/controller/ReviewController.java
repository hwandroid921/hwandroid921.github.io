package com.tour.jeju.controller;

import com.tour.jeju.dto.MemberResponse;
import com.tour.jeju.dto.ReviewRequest;
import com.tour.jeju.dto.ReviewResponse;
import com.tour.jeju.service.ReviewService;
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
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    // 리뷰 리스트
    @GetMapping("/list/{attractionId}")
    public String list(@PathVariable Long attractionId, Model model) {
        model.addAttribute("reviews", reviewService.getReviewList(attractionId));
        model.addAttribute("attractionId", attractionId);
        return "review/list";
    }

    // 리뷰 작성 폼
    @GetMapping("/insert/{attractionId}")
    public String insertForm(@PathVariable Long attractionId,
                             Model model, HttpSession session) {
        if (session.getAttribute("loginMember") == null) {
            return "redirect:/member/login";
        }
        model.addAttribute("reviewRequest", new ReviewRequest());
        model.addAttribute("attractionId", attractionId);
        return "review/insert";
    }

    // 리뷰 작성 처리
    @PostMapping("/insert/{attractionId}")
    public String insert(@PathVariable Long attractionId,
                         @Valid @ModelAttribute ReviewRequest request,
                         BindingResult bindingResult,
                         @RequestParam(value = "image", required = false) MultipartFile image,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) return "review/insert";

        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        if (loginMember == null) return "redirect:/member/login";

        try {
            Long reviewId = reviewService.insertReview(
                    attractionId, loginMember.getId(), request, image
            );
            redirectAttributes.addFlashAttribute("message", "리뷰가 등록되었습니다.");
            return "redirect:/review/detail/" + reviewId;

        } catch (Exception e) {
            bindingResult.reject("insertFailed", "등록에 실패했습니다: " + e.getMessage());
            return "review/insert";
        }
    }

    // 리뷰 수정 폼
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("loginMember") == null) {
            return "redirect:/member/login";
        }
        ReviewResponse review = reviewService.getReview(id);
        model.addAttribute("reviewRequest", ReviewRequest.fromResponse(review));
        model.addAttribute("review", review);
        return "review/edit";
    }

    // 리뷰 수정 처리
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       @Valid @ModelAttribute ReviewRequest request,
                       BindingResult bindingResult,
                       @RequestParam(value = "image", required = false) MultipartFile image,
                       HttpSession session,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) return "review/edit";

        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        if (loginMember == null) return "redirect:/member/login";

        try {
            reviewService.editReview(id, loginMember.getId(), request, image);
            redirectAttributes.addFlashAttribute("message", "리뷰가 수정되었습니다.");
            return "redirect:/review/detail/" + id;

        } catch (Exception e) {
            bindingResult.reject("editFailed", "수정에 실패했습니다: " + e.getMessage());
            return "review/edit";
        }
    }

    // 리뷰 삭제
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {

        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        if (loginMember == null) return "redirect:/member/login";

        try {
            Long attractionId = reviewService.getReview(id).getAttractionId();
            reviewService.deleteReview(id, loginMember.getId());
            redirectAttributes.addFlashAttribute("message", "리뷰가 삭제되었습니다.");
            return "redirect:/attraction/detail/" + attractionId;

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/review/detail/" + id;
        }
    }
}

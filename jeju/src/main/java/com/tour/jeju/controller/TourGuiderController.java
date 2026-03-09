package com.tour.jeju.controller;

import com.tour.jeju.dto.MemberResponse;
import com.tour.jeju.dto.TourGuideRequest;
import com.tour.jeju.dto.TourGuideResponse;
import com.tour.jeju.service.TourGuideService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/community/tourguide")
public class TourGuiderController {

    private final TourGuideService tourGuideService;

    // 목록
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "9") int size,
                       @RequestParam(required = false) String searchType,
                       @RequestParam(required = false) String keyword,
                       Model model) {

        Page<TourGuideResponse> boardPage = tourGuideService.getTravelogueList(page - 1, size, searchType, keyword);
        model.addAttribute("travelogueList", boardPage.getContent());

        int totalPages = boardPage.getTotalPages() == 0 ? 1 : boardPage.getTotalPages();
        int currentPage = boardPage.getNumber() + 1;
        int pageBlockSize = 10;
        int startPage = ((currentPage - 1) / pageBlockSize) * pageBlockSize + 1;
        int endPage = Math.min(startPage + pageBlockSize - 1, totalPages);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "community/tourguide/list";
    }

    // 상세
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id,
                         HttpSession session,
                         Model model) {
        TourGuideResponse travelogue = tourGuideService.getTravelogue(id);
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");

        boolean isWriter = false;
        if (loginMember != null) {
            isWriter = travelogue.getAuthorId().equals(loginMember.getId());
        }

        model.addAttribute("travelogue", travelogue);
        model.addAttribute("isWriter", isWriter);
        return "community/tourguide/detail";
    }

    // 작성 폼
    @GetMapping("/write")
    public String writeForm(HttpSession session, Model model) {
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/member/login?redirectURL=/community/tourguide/write";
        }
        model.addAttribute("tourGuideRequest", new TourGuideRequest());
        return "community/tourguide/write";
    }

    // 작성 처리
    @PostMapping("/write")
    public String write(@Valid @ModelAttribute TourGuideRequest request,
                        BindingResult bindingResult,
                        @RequestParam(value = "images", required = false) List<MultipartFile> images,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "community/tourguide/write";
        }
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/member/login";
        }
        try {
            Long id = tourGuideService.writeTravelogue(request, loginMember.getId(), images);
            redirectAttributes.addFlashAttribute("message", "후기가 등록되었습니다.");
            return "redirect:/community/tourguide/detail/" + id;
        } catch (IllegalArgumentException e) {
            log.info("여행후기 작성 실패", e);
            bindingResult.reject("writeFailed", "등록에 실패했습니다.");
            return "community/tourguide/write";
        }
    }

    // 수정 폼
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id,
                           HttpSession session,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/member/login";
        }
        try {
            TourGuideResponse travelogue = tourGuideService.getTravelogueForEdit(id, loginMember.getId());
            TourGuideRequest request = TourGuideRequest.builder()
                    .title(travelogue.getTitle())
                    .content(travelogue.getContent())
                    .build();
            model.addAttribute("tourGuideRequest", request);
            model.addAttribute("travelogueId", id);
            model.addAttribute("existingImgUrls", travelogue.getImgUrls());
            return "community/tourguide/edit";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/community/tourguide/detail/" + id;
        }
    }

    // 수정 처리
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @Valid @ModelAttribute TourGuideRequest request,
                       BindingResult bindingResult,
                       @RequestParam(value = "keepImages", required = false) List<String> keepImages,
                       @RequestParam(value = "newImages", required = false) List<MultipartFile> newImages,
                       HttpSession session,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("travelogueId", id);
            return "community/tourguide/edit";
        }
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/member/login";
        }
        try {
            tourGuideService.editTravelogue(id, request, loginMember.getId(), keepImages, newImages);
            redirectAttributes.addFlashAttribute("message", "후기가 수정되었습니다.");
            return "redirect:/community/tourguide/detail/" + id;
        } catch (IllegalArgumentException e) {
            bindingResult.reject("editFailed", e.getMessage());
            model.addAttribute("travelogueId", id);
            return "community/tourguide/edit";
        }
    }

    // 삭제
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {
        MemberResponse loginMember = (MemberResponse) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/member/login";
        }
        try {
            tourGuideService.deleteTravelogue(id, loginMember.getId());
            redirectAttributes.addFlashAttribute("message", "후기가 삭제되었습니다.");
            return "redirect:/community/tourguide/list";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/community/tourguide/detail/" + id;
        }
    }
}

package com.tour.jeju.controller;

import com.tour.jeju.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/accommodation")
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping("/hotel")
    public String hotel(Model model) {
        model.addAttribute("accommodationList", accommodationService.getByCategory("hotel"));
        return "accommadation/hotel";
    }

    @GetMapping("/pension")
    public String pension(Model model) {
        model.addAttribute("accommodationList", accommodationService.getByCategory("pension"));
        return "accommadation/pension";
    }

    @GetMapping("/guesthouse")
    public String guesthouse(Model model) {
        model.addAttribute("accommodationList", accommodationService.getByCategory("guesthouse"));
        return "accommadation/guesthouse";
    }

    @GetMapping("/bnb")
    public String bnb(Model model) {
        model.addAttribute("accommodationList", accommodationService.getByCategory("bnb"));
        return "accommadation/bnb";
    }
}

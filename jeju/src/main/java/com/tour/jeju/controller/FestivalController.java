package com.tour.jeju.controller;

import com.tour.jeju.dto.FestivalRequest;
import com.tour.jeju.entity.Festival;
import com.tour.jeju.service.FestivalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/festival")
public class FestivalController {
    private final FestivalService festivalService;

    //목록보기
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", festivalService.findAll());
        return "festival/list";
    }
    //상세보기
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Festival festival = festivalService.findById(id);
        model.addAttribute("festival", festival);

        if (id == 1) {
            return "festival/sungsan";
        }
        else if (id == 2) {
            return "festival/olle";
        }

        return "festival/detail";
    }
    //등록 폼 로딩
    @GetMapping("/insert")
    public String festivalForm(Model model) {
        model.addAttribute("festivalRequest", new FestivalRequest());
        return "festival/form";
    }
    //등록 처리
    @PostMapping("/insert")
    public String write(@Valid @ModelAttribute FestivalRequest dto,
                        BindingResult bindingResult,
                        Model model) {
        try {
            festivalService.insert(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "festival/form";
    }
    //수정 폼
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute FestivalRequest dto){
        try {
            festivalService.edit(id, dto);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/festival/update/"+id;
    }

    //수정 처리
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Festival festival = festivalService.findById(id);

        FestivalRequest dto = new FestivalRequest();
        dto.setName(festival.getName());
        dto.setDescription(festival.getDescription());
        dto.setUrlPath2(festival.getUrlPath2());
        dto.setUrlPath3(festival.getUrlPath3());
        dto.setUrlPath4(festival.getUrlPath4());
        dto.setUrlPath5(festival.getUrlPath5());
        dto.setUrlPath6(festival.getUrlPath6());
        dto.setUrlPath7(festival.getUrlPath7());
        dto.setUrlPath8(festival.getUrlPath8());

        return "event/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        festivalService.delete(id);
        return "redirect:/festival/list";
    }
}

package com.tour.jeju.controller;

import com.tour.jeju.dto.CultureRequest;
import com.tour.jeju.entity.Culture;
import com.tour.jeju.service.CultureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/culture")
public class CultureController {
    private final CultureService cultureService;

    //목록보기
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", cultureService.findAll());
        return "culture/list";
    }
    //상세보기
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Culture culture = cultureService.findById(id);
        model.addAttribute("culture", culture);

        return "culture/detail";
    }
    //등록 폼 로딩
    @GetMapping("/insert")
    public String cultureForm(Model model) {
        model.addAttribute("cultureRequest", new CultureRequest());
        return "culture/form";
    }
    //등록 처리
    @PostMapping("/insert")
    public String write(@Valid @ModelAttribute CultureRequest dto,
                        BindingResult bindingResult,
                        Model model) {
        try {
            cultureService.insert(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "culture/form";
    }
    //수정 폼
    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute CultureRequest dto){
        try {
            cultureService.edit(id, dto);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/culture/edit/"+id;
    }

    //수정 처리
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Culture culture = cultureService.findById(id);

        CultureRequest dto = new CultureRequest();
        dto.setName(culture.getName());
        dto.setDescription(culture.getDescription());

        return "event/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        cultureService.delete(id);
        return "redirect:/culture/list";
    }
}
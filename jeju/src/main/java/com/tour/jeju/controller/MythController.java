package com.tour.jeju.controller;

import com.tour.jeju.dto.MythRequest;
import com.tour.jeju.entity.Myth;
import com.tour.jeju.service.MythService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myth")
public class MythController {
    private final MythService mythService;

    //목록보기
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", mythService.findAll());
        return "myth/list";
    }
    //상세보기
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Myth myth = mythService.findById(id);
        model.addAttribute("myth", myth);

        return "myth/detail";
    }
    //등록 폼 로딩
    @GetMapping("/insert")
    public String mythForm(Model model) {
        model.addAttribute("mythRequest", new MythRequest());
        return "myth/form";
    }
    //등록 처리
    @PostMapping("/insert")
    public String write(@Valid @ModelAttribute MythRequest dto,
                        BindingResult bindingResult,
                        Model model) {
        try {
            mythService.insert(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "myth/form";
    }
    //수정 폼
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute MythRequest dto){
        try {
            mythService.edit(id, dto);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/myth/update/"+id;
    }

    //수정 처리
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Myth myth = mythService.findById(id);

        MythRequest dto = new MythRequest();
        dto.setName(myth.getName());
        dto.setDescription(myth.getDescription());
        dto.setUrlPath2(myth.getUrlPath2());
        dto.setUrlPath3(myth.getUrlPath3());
        dto.setUrlPath4(myth.getUrlPath4());
        dto.setUrlPath5(myth.getUrlPath5());
        dto.setUrlPath6(myth.getUrlPath6());
        dto.setUrlPath7(myth.getUrlPath7());
        dto.setUrlPath8(myth.getUrlPath8());

        return "event/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        mythService.delete(id);
        return "redirect:/myth/list";
    }
}

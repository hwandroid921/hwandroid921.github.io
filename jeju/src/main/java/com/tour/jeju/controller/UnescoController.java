package com.tour.jeju.controller;

import com.tour.jeju.dto.UnescoRequest;
import com.tour.jeju.entity.Unesco;
import com.tour.jeju.service.UnescoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/unesco")
public class UnescoController {
    private final UnescoService unescoService;

    //목록보기
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", unescoService.findAll());
        return "unesco/list";
    }
    //상세보기
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Unesco unesco = unescoService.findById(id);
        model.addAttribute("unesco", unesco);

        return "unesco/detail";
    }
    //등록 폼 로딩
    @GetMapping("/insert")
    public String unescoForm(Model model) {
        model.addAttribute("unescoRequest", new UnescoRequest());
        return "unesco/form";
    }
    //등록 처리
    @PostMapping("/insert")
    public String write(@Valid @ModelAttribute UnescoRequest dto,
                        BindingResult bindingResult,
                        Model model) {
        try {
            unescoService.insert(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "unesco/form";
    }
    //수정 폼
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute UnescoRequest dto){
        try {
            unescoService.edit(id, dto);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/unesco/edit/"+id;
    }

    //수정 처리
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Unesco unesco = unescoService.findById(id);

        UnescoRequest dto = new UnescoRequest();
        dto.setName(unesco.getName());
        dto.setDescription(unesco.getDescription());
        dto.setUrlPath2(unesco.getUrlPath2());
        dto.setUrlPath3(unesco.getUrlPath3());
        dto.setUrlPath4(unesco.getUrlPath4());
        dto.setUrlPath5(unesco.getUrlPath5());
        dto.setUrlPath6(unesco.getUrlPath6());
        dto.setUrlPath7(unesco.getUrlPath7());
        dto.setUrlPath8(unesco.getUrlPath8());

        return "event/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        unescoService.delete(id);
        return "redirect:/unesco/list";
    }
}

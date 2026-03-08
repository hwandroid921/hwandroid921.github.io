package com.tour.jeju.controller;

import com.tour.jeju.entity.Event;
import com.tour.jeju.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.tour.jeju.dto.EventRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    //목록보기
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", eventService.findAll());
        return "event/list";
    }
    //상세보기
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Event event = eventService.findById(id);
        model.addAttribute("event", event);

        return "event/detail";
    }
    //등록 폼 로딩
    @GetMapping("/insert")
    public String eventForm(Model model) {
        model.addAttribute("eventRequest", new EventRequest());
        return "event/form";
    }
    //등록 처리
    @PostMapping("/insert")
    public String write(@Valid @ModelAttribute EventRequest dto,
                        BindingResult bindingResult,
                        Model model) {
        try {
            eventService.insert(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "event/form";
    }
    //수정 폼
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Event event = eventService.findById(id);

        EventRequest dto = new EventRequest();
        dto.setName(event.getName());
        dto.setPeriod(event.getPeriod());
        dto.setLinkPage(event.getLinkPage());

        return "event/form";
    }

    //수정 처리
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute EventRequest dto){
        try {
            eventService.edit(id, dto);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/event/edit/"+id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        eventService.delete(id);
        return "redirect:/event/list";
    }
}
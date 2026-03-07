package com.shop.basicwear.item.controller;

import com.shop.basicwear.item.dto.ItemRead;
import com.shop.basicwear.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/api/items")
    public ResponseEntity<?> readAll() {
        List<ItemRead> items = itemService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}

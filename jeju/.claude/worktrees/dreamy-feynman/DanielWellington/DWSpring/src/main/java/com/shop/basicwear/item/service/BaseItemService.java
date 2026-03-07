package com.shop.basicwear.item.service;

import com.shop.basicwear.item.dto.ItemRead;
import com.shop.basicwear.item.entity.Item;
import com.shop.basicwear.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseItemService implements ItemService{
    private final ItemRepository itemRepository;

    // 전체 상품 목록 조회
    @Override
    public List<ItemRead> findAll() {
        return itemRepository.findAll().stream().map(Item::toRead).toList();
    }

    // 특정 아이디 리스트로 상품 목록 조회
    @Override
    public List<ItemRead> findAll(List<Integer> ids) {
        return itemRepository.findAllByIdIn(ids).stream().map(Item::toRead).toList();
    }
}

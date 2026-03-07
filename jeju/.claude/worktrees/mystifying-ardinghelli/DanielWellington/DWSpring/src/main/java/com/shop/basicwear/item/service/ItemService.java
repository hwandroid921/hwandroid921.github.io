package com.shop.basicwear.item.service;


import com.shop.basicwear.item.dto.ItemRead;

import java.util.List;

public interface ItemService {
    // 전체 상품 목록 조회
    List<ItemRead> findAll();
    // 특정 아이디 리스트로 상품 목록 조회
    List<ItemRead> findAll(List<Integer> ids);
}

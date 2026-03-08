package com.tour.jeju.service;

import com.tour.jeju.dto.FoodRequest;
import com.tour.jeju.dto.FoodResponse;
import com.tour.jeju.entity.Food;
import com.tour.jeju.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    @Transactional(readOnly = true)
    public FoodResponse getFood(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 음식입니다."));
        return FoodResponse.fromEntity(food);
    }

    @Transactional(readOnly = true)
    public List<FoodResponse> getFoodList() {
        return foodRepository.findAll(Sort.by("id").ascending())
                .stream()
                .map(FoodResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<FoodResponse> getPrevFood(Long id) {
        return foodRepository.findFirstByIdLessThanOrderByIdDesc(id)
                .map(FoodResponse::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<FoodResponse> getNextFood(Long id) {
        return foodRepository.findFirstByIdGreaterThanOrderByIdAsc(id)
                .map(FoodResponse::fromEntity);
    }

    @Transactional
    public Long insertFood(FoodRequest request) {
        Food food = request.toEntity();
        return foodRepository.save(food).getId();
    }

    @Transactional
    public void editFood(Long id, FoodRequest request) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 음식입니다."));
        food.update(request.getName(), request.getDescription(), request.getImgUrl());
    }

    @Transactional
    public void deleteFood(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 음식입니다."));
        foodRepository.delete(food);
    }
}

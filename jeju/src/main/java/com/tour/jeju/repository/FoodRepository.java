package com.tour.jeju.repository;

import com.tour.jeju.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    // 현재 id보다 작은 것 중 가장 큰 id → 이전 음식
    Optional<Food> findFirstByIdLessThanOrderByIdDesc(Long id);

    // 현재 id보다 큰 것 중 가장 작은 id → 다음 음식
    Optional<Food> findFirstByIdGreaterThanOrderByIdAsc(Long id);
}

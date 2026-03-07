package com.tour.jeju.dto;

import com.tour.jeju.entity.Food;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FoodResponse {

    private Long id;
    private String name;
    private String description;
    private String imgUrl;

    public static FoodResponse fromEntity(Food food) {
        return FoodResponse.builder()
                .id(food.getId())
                .name(food.getName())
                .description(food.getDescription())
                .imgUrl(food.getImgUrl())
                .build();
    }
}

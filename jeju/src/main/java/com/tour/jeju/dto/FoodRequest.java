package com.tour.jeju.dto;

import com.tour.jeju.entity.Food;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FoodRequest {

    @NotBlank(message = "음식 이름은 필수 입력 항목입니다.")
    private String name;

    private String description;

    @NotBlank(message = "이미지 URL은 필수 입력 항목입니다.")
    private String imgUrl;

    public Food toEntity() {
        return Food.builder()
                .name(name)
                .description(description)
                .imgUrl(imgUrl)
                .build();
    }
}

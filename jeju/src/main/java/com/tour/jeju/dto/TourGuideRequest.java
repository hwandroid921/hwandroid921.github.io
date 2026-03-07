package com.tour.jeju.dto;

import com.tour.jeju.entity.TourGuide;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TourGuideRequest {

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    private String title;

    private String content;
    private String imgUrl;

    public TourGuide toEntity() {
        return TourGuide.builder()
                .title(title)
                .content(content)
                .imgUrl(imgUrl)
                .build();
    }
}

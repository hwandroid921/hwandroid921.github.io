package com.tour.jeju.dto;

import com.tour.jeju.entity.TourGuide;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TourGuideResponse {

    private Long id;
    private String title;
    private String content;
    private String imgUrl;
    private LocalDateTime createdAt;

    public static TourGuideResponse fromEntity(TourGuide tourGuide) {
        return TourGuideResponse.builder()
                .id(tourGuide.getId())
                .title(tourGuide.getTitle())
                .content(tourGuide.getContent())
                .imgUrl(tourGuide.getImgUrl())
                .createdAt(tourGuide.getCreatedAt())
                .build();
    }
}

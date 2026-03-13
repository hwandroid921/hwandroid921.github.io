package com.tour.jeju.dto;

import com.tour.jeju.entity.TourGuide;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TourGuideResponse {

    private Long id;
    private String title;
    private String content;
    private List<String> imgUrls;
    private String thumbnailUrl;   // 목록 카드용 - imgUrls 첫번째 요소
    private Long authorId;
    private String author;
    private int views;
    private LocalDateTime createdAt;

    public static TourGuideResponse fromEntity(TourGuide tourGuide) {
        List<String> imgUrls = tourGuide.getImgUrls() != null ? tourGuide.getImgUrls() : new ArrayList<>();
        String thumbnailUrl = imgUrls.isEmpty() ? null : imgUrls.get(0);
        return TourGuideResponse.builder()
                .id(tourGuide.getId())
                .title(tourGuide.getTitle())
                .content(tourGuide.getContent())
                .imgUrls(imgUrls)
                .thumbnailUrl(thumbnailUrl)
                .authorId(tourGuide.getMember().getId())
                .author(tourGuide.getMember().getName())
                .views(tourGuide.getViews())
                .createdAt(tourGuide.getCreatedAt())
                .build();
    }
}

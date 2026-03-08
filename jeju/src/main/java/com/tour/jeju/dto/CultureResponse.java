package com.tour.jeju.dto;

import com.tour.jeju.entity.Culture;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CultureResponse {

    private Long id;
    private String name;
    private String description;
    private String urlPath1;
    private String urlPath2;
    private String urlPath3;
    private String urlPath4;
    private String urlPath5;
    private String urlPath6;
    private String urlPath7;
    private String urlPath8;
    private LocalDateTime createdAt;

    public static CultureResponse fromEntity(Culture culture) {
        return CultureResponse.builder()
                .id(culture.getId()).name(culture.getName())
                .description(culture.getDescription())
                .urlPath1(culture.getUrlPath1()).urlPath2(culture.getUrlPath2())
                .urlPath3(culture.getUrlPath3()).urlPath4(culture.getUrlPath4())
                .urlPath5(culture.getUrlPath5()).urlPath6(culture.getUrlPath6())
                .urlPath7(culture.getUrlPath7()).urlPath8(culture.getUrlPath8())
                .createdAt(culture.getCreatedAt())
                .build();
    }
}
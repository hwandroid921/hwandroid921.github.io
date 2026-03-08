package com.tour.jeju.dto;

import com.tour.jeju.entity.Unesco;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UnescoResponse {

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

    public static UnescoResponse fromEntity(Unesco unesco) {
        return UnescoResponse.builder()
                .id(unesco.getId()).name(unesco.getName())
                .description(unesco.getDescription())
                .urlPath1(unesco.getUrlPath1()).urlPath2(unesco.getUrlPath2())
                .urlPath3(unesco.getUrlPath3()).urlPath4(unesco.getUrlPath4())
                .urlPath5(unesco.getUrlPath5()).urlPath6(unesco.getUrlPath6())
                .urlPath7(unesco.getUrlPath7()).urlPath8(unesco.getUrlPath8())
                .createdAt(unesco.getCreatedAt())
                .build();
    }
}

package com.tour.jeju.dto;

import com.tour.jeju.entity.Myth;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MythResponse {

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

    public static MythResponse fromEntity(Myth myth) {
        return MythResponse.builder()
                .id(myth.getId()).name(myth.getName())
                .description(myth.getDescription())
                .urlPath1(myth.getUrlPath1()).urlPath2(myth.getUrlPath2())
                .urlPath3(myth.getUrlPath3()).urlPath4(myth.getUrlPath4())
                .urlPath5(myth.getUrlPath5()).urlPath6(myth.getUrlPath6())
                .urlPath7(myth.getUrlPath7()).urlPath8(myth.getUrlPath8())
                .createdAt(myth.getCreatedAt())
                .build();
    }
}

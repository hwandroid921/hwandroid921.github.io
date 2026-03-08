package com.tour.jeju.dto;

import com.tour.jeju.entity.Festival;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FestivalResponse {

    private Long id;
    private String name;

    private String address;
    private String phoneNumber;
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

    public static FestivalResponse fromEntity(Festival festival) {
        return FestivalResponse.builder()
                .id(festival.getId()).name(festival.getName())
                .address(festival.getAddress()).phoneNumber(festival.getPhoneNumber())
                .description(festival.getDescription())
                .urlPath1(festival.getUrlPath1()).urlPath2(festival.getUrlPath2())
                .urlPath3(festival.getUrlPath3()).urlPath4(festival.getUrlPath4())
                .urlPath5(festival.getUrlPath5()).urlPath6(festival.getUrlPath6())
                .urlPath7(festival.getUrlPath7()).urlPath8(festival.getUrlPath8())
                .createdAt(festival.getCreatedAt())
                .build();
    }
}

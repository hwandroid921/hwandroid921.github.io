package com.tour.jeju.dto;

import com.tour.jeju.entity.Festival;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FestivalResponse {

    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private String imgUrl1;
    private String imgUrl2;
    private String imgUrl3;
    private String imgUrl4;
    private String imgUrl5;
    private String imgUrl6;
    private String imgUrl7;
    private String imgUrl8;
    private LocalDateTime createdAt;

    public static FestivalResponse fromEntity(Festival festival) {
        return FestivalResponse.builder()
                .id(festival.getId()).name(festival.getName())
                .description(festival.getDescription()).location(festival.getLocation())
                .startDate(festival.getStartDate()).endDate(festival.getEndDate())
                .imgUrl1(festival.getImgUrl1()).imgUrl2(festival.getImgUrl2())
                .imgUrl3(festival.getImgUrl3()).imgUrl4(festival.getImgUrl4())
                .imgUrl5(festival.getImgUrl5()).imgUrl6(festival.getImgUrl6())
                .imgUrl7(festival.getImgUrl7()).imgUrl8(festival.getImgUrl8())
                .createdAt(festival.getCreatedAt())
                .build();
    }
}

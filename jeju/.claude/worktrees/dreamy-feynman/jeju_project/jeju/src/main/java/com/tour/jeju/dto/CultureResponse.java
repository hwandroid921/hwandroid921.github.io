package com.tour.jeju.dto;

import com.tour.jeju.entity.Culture;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CultureResponse {

    private Long id;
    private String name;
    private String description;
    private String imgUrl1;
    private String imgUrl2;
    private String imgUrl3;
    private String imgUrl4;
    private String imgUrl5;
    private String imgUrl6;
    private String imgUrl7;
    private String imgUrl8;
    private LocalDateTime createdAt;

    public static CultureResponse fromEntity(Culture culture) {
        return CultureResponse.builder()
                .id(culture.getId()).name(culture.getName())
                .description(culture.getDescription())
                .imgUrl1(culture.getImgUrl1()).imgUrl2(culture.getImgUrl2())
                .imgUrl3(culture.getImgUrl3()).imgUrl4(culture.getImgUrl4())
                .imgUrl5(culture.getImgUrl5()).imgUrl6(culture.getImgUrl6())
                .imgUrl7(culture.getImgUrl7()).imgUrl8(culture.getImgUrl8())
                .createdAt(culture.getCreatedAt())
                .build();
    }
}

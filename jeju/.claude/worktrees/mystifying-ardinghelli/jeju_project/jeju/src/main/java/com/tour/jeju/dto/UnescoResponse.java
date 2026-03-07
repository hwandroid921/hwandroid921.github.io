package com.tour.jeju.dto;

import com.tour.jeju.entity.Unesco;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UnescoResponse {

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

    public static UnescoResponse fromEntity(Unesco unesco) {
        return UnescoResponse.builder()
                .id(unesco.getId()).name(unesco.getName())
                .description(unesco.getDescription())
                .imgUrl1(unesco.getImgUrl1()).imgUrl2(unesco.getImgUrl2())
                .imgUrl3(unesco.getImgUrl3()).imgUrl4(unesco.getImgUrl4())
                .imgUrl5(unesco.getImgUrl5()).imgUrl6(unesco.getImgUrl6())
                .imgUrl7(unesco.getImgUrl7()).imgUrl8(unesco.getImgUrl8())
                .createdAt(unesco.getCreatedAt())
                .build();
    }
}

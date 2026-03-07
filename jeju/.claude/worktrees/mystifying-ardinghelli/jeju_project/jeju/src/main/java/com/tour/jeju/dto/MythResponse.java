package com.tour.jeju.dto;

import com.tour.jeju.entity.Myth;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MythResponse {

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

    public static MythResponse fromEntity(Myth myth) {
        return MythResponse.builder()
                .id(myth.getId()).name(myth.getName())
                .description(myth.getDescription())
                .imgUrl1(myth.getImgUrl1()).imgUrl2(myth.getImgUrl2())
                .imgUrl3(myth.getImgUrl3()).imgUrl4(myth.getImgUrl4())
                .imgUrl5(myth.getImgUrl5()).imgUrl6(myth.getImgUrl6())
                .imgUrl7(myth.getImgUrl7()).imgUrl8(myth.getImgUrl8())
                .createdAt(myth.getCreatedAt())
                .build();
    }
}

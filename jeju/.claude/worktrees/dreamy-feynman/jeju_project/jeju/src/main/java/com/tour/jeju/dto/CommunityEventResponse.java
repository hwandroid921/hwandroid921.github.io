package com.tour.jeju.dto;

import com.tour.jeju.entity.CommunityEvent;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CommunityEventResponse {

    private Long id;
    private String title;
    private String content;
    private String imgUrl1;
    private String imgUrl2;
    private String imgUrl3;
    private String imgUrl4;
    private String imgUrl5;
    private String imgUrl6;
    private String imgUrl7;
    private String imgUrl8;
    private String authorName;
    private LocalDateTime createdAt;

    public static CommunityEventResponse fromEntity(CommunityEvent event) {
        return CommunityEventResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .content(event.getContent())
                .imgUrl1(event.getImgUrl1()).imgUrl2(event.getImgUrl2())
                .imgUrl3(event.getImgUrl3()).imgUrl4(event.getImgUrl4())
                .imgUrl5(event.getImgUrl5()).imgUrl6(event.getImgUrl6())
                .imgUrl7(event.getImgUrl7()).imgUrl8(event.getImgUrl8())
                .authorName(event.getMember().getName())
                .createdAt(event.getCreatedAt())
                .build();
    }
}

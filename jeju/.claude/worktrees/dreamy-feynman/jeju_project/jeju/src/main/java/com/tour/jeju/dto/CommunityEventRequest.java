package com.tour.jeju.dto;

import com.tour.jeju.entity.CommunityEvent;
import com.tour.jeju.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CommunityEventRequest {

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 항목입니다.")
    private String content;

    private String imgUrl1;
    private String imgUrl2;
    private String imgUrl3;
    private String imgUrl4;
    private String imgUrl5;
    private String imgUrl6;
    private String imgUrl7;
    private String imgUrl8;

    public CommunityEvent toEntity(Member member) {
        return CommunityEvent.builder()
                .title(title)
                .content(content)
                .imgUrl1(imgUrl1).imgUrl2(imgUrl2).imgUrl3(imgUrl3).imgUrl4(imgUrl4)
                .imgUrl5(imgUrl5).imgUrl6(imgUrl6).imgUrl7(imgUrl7).imgUrl8(imgUrl8)
                .member(member)
                .build();
    }
}

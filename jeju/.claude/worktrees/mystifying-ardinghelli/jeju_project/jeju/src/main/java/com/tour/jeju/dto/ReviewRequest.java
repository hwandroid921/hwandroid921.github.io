package com.tour.jeju.dto;

import com.tour.jeju.entity.Attraction;
import com.tour.jeju.entity.Member;
import com.tour.jeju.entity.Review;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewRequest {

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

    public Review toEntity(Member member, Attraction attraction) {
        return Review.builder()
                .title(title)
                .content(content)
                .imgUrl1(imgUrl1).imgUrl2(imgUrl2).imgUrl3(imgUrl3).imgUrl4(imgUrl4)
                .imgUrl5(imgUrl5).imgUrl6(imgUrl6).imgUrl7(imgUrl7).imgUrl8(imgUrl8)
                .memberId(member)
                .attractionID(attraction)
                .build();
    }
}

package com.tour.jeju.dto;

import com.tour.jeju.entity.Review;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewResponse {

    private Long id;
    private String title;
    private String content;
    private String maskedAuthorName;
    private String imgUrl1;
    private String imgUrl2;
    private String imgUrl3;
    private String imgUrl4;
    private String imgUrl5;
    private String imgUrl6;
    private String imgUrl7;
    private String imgUrl8;
    private LocalDateTime createdAt;

    public static ReviewResponse fromEntity(Review review) {
        String name = review.getMemberId().getName();
        String masked = name.length() <= 1 ? name : name.charAt(0) + "*".repeat(name.length() - 1);

        return ReviewResponse.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .maskedAuthorName(masked)
                .imgUrl1(review.getImgUrl1()).imgUrl2(review.getImgUrl2())
                .imgUrl3(review.getImgUrl3()).imgUrl4(review.getImgUrl4())
                .imgUrl5(review.getImgUrl5()).imgUrl6(review.getImgUrl6())
                .imgUrl7(review.getImgUrl7()).imgUrl8(review.getImgUrl8())
                .createdAt(review.getCreatedAt())
                .build();
    }
}

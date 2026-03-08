package com.tour.jeju.dto;

import com.tour.jeju.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ReviewResponse {

    private Long id;
    private String title;
    private String content;
    private String memberName;   // member.name
    private Long attractionId;
    private String imgUrl; // imgUrl1만
    private LocalDateTime createdAt;

    public static ReviewResponse fromEntity(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .memberName(review.getMember().getName())
                .imgUrl(review.getImgUrl1())
                .createdAt(review.getCreatedAt())
                .build();
    }

}

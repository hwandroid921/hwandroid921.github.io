package com.tour.jeju.dto;

import com.tour.jeju.entity.Attraction;
import com.tour.jeju.entity.Member;
import com.tour.jeju.entity.Review;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<<< HEAD:jeju/.claude/worktrees/mystifying-ardinghelli/jeju_project/jeju/src/main/java/com/tour/jeju/dto/ReviewRequest.java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
========
@Getter
@Setter
@NoArgsConstructor
>>>>>>>> 31bd79988529b81c7888e4640ae298bbf0f89c36:jeju/src/main/java/com/tour/jeju/dto/ReviewRequest.java
public class ReviewRequest {

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    public Review toEntity(Member member, Attraction attraction) {
        return Review.builder()
                .title(this.title)
                .content(this.content)
                .member(member)
                .attraction(attraction)
                .build();
    }

    public static ReviewRequest fromResponse(ReviewResponse response) {
        ReviewRequest request = new ReviewRequest();
        request.setTitle(response.getTitle());
        request.setContent(response.getContent());
        return request;
    }
}
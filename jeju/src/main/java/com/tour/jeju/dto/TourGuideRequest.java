package com.tour.jeju.dto;

import com.tour.jeju.entity.Member;
import com.tour.jeju.entity.TourGuide;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TourGuideRequest {

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    private String title;

    private String content;

    public TourGuide toEntity(Member member, List<String> imgUrls) {
        return TourGuide.builder()
                .title(title)
                .content(content)
                .imgUrls(imgUrls != null ? imgUrls : new ArrayList<>())
                .member(member)
                .build();
    }
}

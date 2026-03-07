package com.tour.jeju.dto;

import com.tour.jeju.entity.Complaint;
import com.tour.jeju.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ComplaintRequest {

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 항목입니다.")
    private String content;

    public Complaint toEntity(Member member) {
        return Complaint.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }
}

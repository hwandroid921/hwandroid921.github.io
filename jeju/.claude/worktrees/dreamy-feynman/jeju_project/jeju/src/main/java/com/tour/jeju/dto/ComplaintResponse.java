package com.tour.jeju.dto;

import com.tour.jeju.entity.Complaint;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ComplaintResponse {

    private Long id;
    private String title;
    private String content;
    private String answer;
    private String authorName;
    private LocalDateTime createdAt;

    public static ComplaintResponse fromEntity(Complaint complaint) {
        return ComplaintResponse.builder()
                .id(complaint.getId())
                .title(complaint.getTitle())
                .content(complaint.getContent())
                .answer(complaint.getAnswer())
                .authorName(complaint.getMember().getName())
                .createdAt(complaint.getCreatedAt())
                .build();
    }
}

package com.tour.jeju.dto;

import com.tour.jeju.entity.Notice;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NoticeResponse {

    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private String authorName;
    private Integer views;
    private LocalDateTime createdAt;

    // Entity를 DTO로 변환
    public static NoticeResponse fromEntity(Notice notice) {
        return NoticeResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .authorId(notice.getMember().getId())
                .authorName(notice.getMember().getName())
                .views(notice.getViews())
                .createdAt(notice.getCreatedAt())
                .build();
    }

}

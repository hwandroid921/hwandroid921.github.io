package com.tour.jeju.dto;

import com.tour.jeju.entity.Event;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EventResponse {

    private Long id;
    private String name;
    private String period;
    private String linkPage;
    private String urlPath;
    private LocalDateTime createdAt;

    public static EventResponse fromEntity(Event event) {
        return EventResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .period(event.getPeriod())
                .linkPage(event.getLinkPage())
                .urlPath(event.getUrlPath())
                .createdAt(event.getCreatedAt())
                .build();
    }
}

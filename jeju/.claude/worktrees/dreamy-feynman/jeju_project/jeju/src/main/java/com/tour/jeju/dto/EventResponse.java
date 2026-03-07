package com.tour.jeju.dto;

import com.tour.jeju.entity.Event;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EventResponse {

    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private String imgUrl1;
    private String imgUrl2;
    private String imgUrl3;
    private String imgUrl4;
    private String imgUrl5;
    private String imgUrl6;
    private String imgUrl7;
    private String imgUrl8;
    private LocalDateTime createdAt;

    public static EventResponse fromEntity(Event event) {
        return EventResponse.builder()
                .id(event.getId()).name(event.getName())
                .description(event.getDescription()).location(event.getLocation())
                .startDate(event.getStartDate()).endDate(event.getEndDate())
                .imgUrl1(event.getImgUrl1()).imgUrl2(event.getImgUrl2())
                .imgUrl3(event.getImgUrl3()).imgUrl4(event.getImgUrl4())
                .imgUrl5(event.getImgUrl5()).imgUrl6(event.getImgUrl6())
                .imgUrl7(event.getImgUrl7()).imgUrl8(event.getImgUrl8())
                .createdAt(event.getCreatedAt())
                .build();
    }
}

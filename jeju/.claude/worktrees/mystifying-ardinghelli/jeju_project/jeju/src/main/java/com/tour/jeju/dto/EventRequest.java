package com.tour.jeju.dto;

import com.tour.jeju.entity.Event;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EventRequest {

    @NotBlank(message = "행사명은 필수 입력 항목입니다.")
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

    public Event toEntity() {
        return Event.builder()
                .name(name).description(description).location(location)
                .startDate(startDate).endDate(endDate)
                .imgUrl1(imgUrl1).imgUrl2(imgUrl2).imgUrl3(imgUrl3).imgUrl4(imgUrl4)
                .imgUrl5(imgUrl5).imgUrl6(imgUrl6).imgUrl7(imgUrl7).imgUrl8(imgUrl8)
                .build();
    }
}

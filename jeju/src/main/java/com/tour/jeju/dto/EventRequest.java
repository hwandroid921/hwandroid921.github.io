package com.tour.jeju.dto;

import com.tour.jeju.entity.Event;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EventRequest {

    @NotBlank(message = "행사명은 필수 입력 항목입니다.")
    private String name;
    private String period;
    private String linkPage;

    private MultipartFile urlPath;

//    public Event toEntity() {
//        return Event.builder()
//                .name(name)
//                .period(period)
//                .linkPage(linkPage)
//                .urlPath(urlPath)
//                .build();
//    }
}

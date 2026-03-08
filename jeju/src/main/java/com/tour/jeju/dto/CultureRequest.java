package com.tour.jeju.dto;

import com.tour.jeju.entity.Culture;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CultureRequest {

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;

    private String description;
    private MultipartFile urlPath1;
    private String urlPath2;
    private String urlPath3;
    private String urlPath4;
    private String urlPath5;
    private String urlPath6;
    private String urlPath7;
    private String urlPath8;
}

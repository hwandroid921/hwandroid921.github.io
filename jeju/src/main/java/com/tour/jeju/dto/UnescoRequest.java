package com.tour.jeju.dto;

import com.tour.jeju.entity.Unesco;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UnescoRequest {

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

//    public Unesco toEntity() {
//        return Unesco.builder()
//                .name(name).description(description)
//                .urlPath1(urlPath1).urlPath2(urlPath2).urlPath3(urlPath3).urlPath4(urlPath4)
//                .urlPath5(urlPath5).urlPath6(urlPath6).urlPath7(urlPath7).urlPath8(urlPath8)
//                .build();
//    }
}

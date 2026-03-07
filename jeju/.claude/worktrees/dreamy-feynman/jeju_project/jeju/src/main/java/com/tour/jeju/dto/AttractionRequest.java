package com.tour.jeju.dto;

import com.tour.jeju.entity.Attraction;
import com.tour.jeju.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttractionRequest {

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;
    private String address;
    private String tel;
    private String description;
    private String imgUrl;

    // Entity => dto
    public Attraction toEntity(Member member) {
        return Attraction.builder()
                .name(name)
                .address(address)
                .tel(tel)
                .description(description)
                .imgUrl(imgUrl)
                .build();
    }
}

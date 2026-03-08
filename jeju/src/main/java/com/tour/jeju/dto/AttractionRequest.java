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
    private String category;
    private String address;
    private String tel;
    private String description;
    private String mapUrl;
    private String imgUrl1;
    private String imgUrl2;
    private String imgUrl3;
    private String imgUrl4;
    private String imgUrl5;
    private String imgUrl6;
    private String imgUrl7;
    private String imgUrl8;

    private String slideUrl1;
    private String slideUrl2;
    private String slideUrl3;
    private String slideUrl4;
    private String slideUrl5;

    // Entity => dto
    public Attraction toEntity(Member member) {
        return Attraction.builder()
                .name(name)
                .address(address)
                .tel(tel)
                .description(description)
                .build();
    }
}

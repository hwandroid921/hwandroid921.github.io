package com.tour.jeju.dto;

import com.tour.jeju.entity.Attraction;
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
    private String thumb;

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

    // Entity => DTO
    public static AttractionRequest toDto(AttractionResponse response) {
        AttractionRequest request = new AttractionRequest();
        request.setName(response.getName());
        request.setCategory(response.getCategory());
        request.setAddress(response.getAddress());
        request.setTel(response.getTel());
        request.setDescription(response.getDescription());
        request.setMapUrl(response.getMapUrl());
        return request;
    }
    // Entity <= dto
    public Attraction toEntity() {
        return Attraction.builder()
                .name(this.name)
                .category(this.category)
                .address(this.address)
                .tel(this.tel)
                .description(this.description)
                .mapUrl(this.mapUrl)
                .build();
    }
}

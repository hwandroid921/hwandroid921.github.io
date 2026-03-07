package com.tour.jeju.dto;

import com.tour.jeju.entity.Accommodation;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AccommodationRequest {

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;

    @NotBlank(message = "카테고리는 필수 입력 항목입니다.")
    private String category;

    private String address;
    private String tel;
    private String homepageUrl;

    private String imgUrl1;
    private String imgUrl2;
    private String imgUrl3;
    private String imgUrl4;
    private String imgUrl5;
    private String imgUrl6;
    private String imgUrl7;
    private String imgUrl8;

    public Accommodation toEntity() {
        return Accommodation.builder()
                .name(name)
                .category(category)
                .address(address)
                .tel(tel)
                .homepageUrl(homepageUrl)
                .imgUrl1(imgUrl1).imgUrl2(imgUrl2).imgUrl3(imgUrl3).imgUrl4(imgUrl4)
                .imgUrl5(imgUrl5).imgUrl6(imgUrl6).imgUrl7(imgUrl7).imgUrl8(imgUrl8)
                .build();
    }
}

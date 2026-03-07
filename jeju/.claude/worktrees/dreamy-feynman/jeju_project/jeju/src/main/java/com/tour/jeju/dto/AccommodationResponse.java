package com.tour.jeju.dto;

import com.tour.jeju.entity.Accommodation;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AccommodationResponse {

    private Long id;
    private String name;
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

    public static AccommodationResponse fromEntity(Accommodation accommodation) {
        return AccommodationResponse.builder()
                .id(accommodation.getId())
                .name(accommodation.getName())
                .category(accommodation.getCategory())
                .address(accommodation.getAddress())
                .tel(accommodation.getTel())
                .homepageUrl(accommodation.getHomepageUrl())
                .imgUrl1(accommodation.getImgUrl1()).imgUrl2(accommodation.getImgUrl2())
                .imgUrl3(accommodation.getImgUrl3()).imgUrl4(accommodation.getImgUrl4())
                .imgUrl5(accommodation.getImgUrl5()).imgUrl6(accommodation.getImgUrl6())
                .imgUrl7(accommodation.getImgUrl7()).imgUrl8(accommodation.getImgUrl8())
                .build();
    }
}

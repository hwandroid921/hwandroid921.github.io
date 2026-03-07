package com.tour.jeju.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String address;
    private String tel;

    @Lob
    private String description;
    private String thumb;

    private String imgUrl1;
    private String imgUrl2;
    private String imgUrl3;
    private String imgUrl4;
    private String imgUrl5;
    private String imgUrl6;
    private String imgUrl7;
    private String imgUrl8;

    private String mapUrl;

    private String slideUrl1;
    private String slideUrl2;
    private String slideUrl3;
    private String slideUrl4;
    private String slideUrl5;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void update(String name, String address, String tel,
                       String description, String thumb,
                       String imgUrl1, String imgUrl2, String imgUrl3, String imgUrl4,
                       String imgUrl5, String imgUrl6, String imgUrl7, String imgUrl8,
                       String mapUrl,
                       String slideUrl1, String slideUrl2, String slideUrl3,
                       String slideUrl4, String slideUrl5) {
        // 필수 항목: 항상 덮어쓰기
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.description = description;
        this.thumb = thumb;

        // 선택 항목: null이면 기존 값 유지
        if (imgUrl1 != null) this.imgUrl1 = imgUrl1;
        if (imgUrl2 != null) this.imgUrl2 = imgUrl2;
        if (imgUrl3 != null) this.imgUrl3 = imgUrl3;
        if (imgUrl4 != null) this.imgUrl4 = imgUrl4;
        if (imgUrl5 != null) this.imgUrl5 = imgUrl5;
        if (imgUrl6 != null) this.imgUrl6 = imgUrl6;
        if (imgUrl7 != null) this.imgUrl7 = imgUrl7;
        if (imgUrl8 != null) this.imgUrl8 = imgUrl8;

        if (mapUrl != null) this.mapUrl = mapUrl;

        if (slideUrl1 != null) this.slideUrl1 = slideUrl1;
        if (slideUrl2 != null) this.slideUrl2 = slideUrl2;
        if (slideUrl3 != null) this.slideUrl3 = slideUrl3;
        if (slideUrl4 != null) this.slideUrl4 = slideUrl4;
        if (slideUrl5 != null) this.slideUrl5 = slideUrl5;
    }
}

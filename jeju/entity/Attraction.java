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
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.description = description;
        this.thumb = thumb;
        this.imgUrl1 = imgUrl1;
        this.imgUrl2 = imgUrl2;
        this.imgUrl3 = imgUrl3;
        this.imgUrl4 = imgUrl4;
        this.imgUrl5 = imgUrl5;
        this.imgUrl6 = imgUrl6;
        this.imgUrl7 = imgUrl7;
        this.imgUrl8 = imgUrl8;
        this.mapUrl = mapUrl;
        this.slideUrl1 = slideUrl1;
        this.slideUrl2 = slideUrl2;
        this.slideUrl3 = slideUrl3;
        this.slideUrl4 = slideUrl4;
        this.slideUrl5 = slideUrl5;
    }
}

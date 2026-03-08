package com.tour.jeju.entity;

import com.tour.jeju.dto.AttractionRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 50, nullable = false)
    private String category;

    @Column(length = 300, nullable = false)
    private String address;

    @Column(length = 500)
    private String thumb;

    @Column(length = 20)
    private String tel;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 500)
    private String imgUrl1;

    @Column(length = 500)
    private String imgUrl2;

    @Column(length = 500)
    private String imgUrl3;

    @Column(length = 500)
    private String imgUrl4;

    @Column(length = 500)
    private String imgUrl5;

    @Column(length = 500)
    private String imgUrl6;

    @Column(length = 500)
    private String imgUrl7;

    @Column(length = 500)
    private String imgUrl8;


    @Column(length = 500)
    private String mapUrl;


    @Column(length = 500)
    private String slideUrl1;


    @Column(length = 500)
    private String slideUrl2;


    @Column(length = 500)
    private String slideUrl3;


    @Column(length = 500)
    private String slideUrl4;


    @Column(length = 500)
    private String slideUrl5;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 배열로 한 번에 세팅
    public void setImageUrls(String[] urls) {
        if (urls == null) return;
        this.imgUrl1 = urls.length > 0 ? urls[0] : null;
        this.imgUrl2 = urls.length > 1 ? urls[1] : null;
        this.imgUrl3 = urls.length > 2 ? urls[2] : null;
        this.imgUrl4 = urls.length > 3 ? urls[3] : null;
        this.imgUrl5 = urls.length > 4 ? urls[4] : null;
        this.imgUrl6 = urls.length > 5 ? urls[5] : null;
        this.imgUrl7 = urls.length > 6 ? urls[6] : null;
        this.imgUrl8 = urls.length > 7 ? urls[7] : null;
    }

    public void setSlideUrls(String[] urls) {
        if (urls == null) return;
        this.slideUrl1 = urls.length > 0 ? urls[0] : null;
        this.slideUrl2 = urls.length > 1 ? urls[1] : null;
        this.slideUrl3 = urls.length > 2 ? urls[2] : null;
        this.slideUrl4 = urls.length > 3 ? urls[3] : null;
        this.slideUrl5 = urls.length > 4 ? urls[4] : null;
    }

    // 기존 URL List로 추출 (null 제외)
    public List<String> getImageUrls() {
        return Stream.of(imgUrl1, imgUrl2, imgUrl3, imgUrl4,
                        imgUrl5, imgUrl6, imgUrl7, imgUrl8)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<String> getSlideUrls() {
        return Stream.of(slideUrl1, slideUrl2, slideUrl3, slideUrl4, slideUrl5)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void update(AttractionRequest dto) {
        this.name        = dto.getName();
        this.category    = dto.getCategory();
        this.address     = dto.getAddress();
        this.tel         = dto.getTel();
        this.description = dto.getDescription();
        this.mapUrl      = dto.getMapUrl();
    }
}
package com.tour.jeju.entity;

import com.tour.jeju.dto.AttractionRequest;
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

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 50, nullable = false)
    private String category;

    @Column(length = 300, nullable = false)
    private String address;

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

    public void update(AttractionRequest request) {
        this.name = request.getName();
        this.category = request.getCategory();
        this.address = request.getAddress();
        this.tel = request.getTel();
        this.description = request.getDescription();
    }
}
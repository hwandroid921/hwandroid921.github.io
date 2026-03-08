package com.tour.jeju.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "accommodation")
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(nullable = false, length = 300)
    private String address;

    @Column(length = 20)
    private String tel;

    @Column(length = 500)
    private String homepageUrl;

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

}

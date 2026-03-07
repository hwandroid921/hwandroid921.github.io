package com.tour.jeju.entity;

import com.tour.jeju.dto.AttractionRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String imgUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void updateInfo(AttractionRequest request) {
        this.name = request.getName();
        this.address = request.getAddress();
        this.tel = request.getTel();
        this.description = request.getDescription();
        this.imgUrl = request.getImgUrl();
    }
}

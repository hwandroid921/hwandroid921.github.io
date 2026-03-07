package com.shop.basicwear.item.entity;

import com.shop.basicwear.item.dto.ItemRead;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String name;
    // 이미지 경로
    @Column(length = 100, nullable = false)
    private String imgPath;
    // 가격
    @Column(nullable = false)
    private Integer price;
    // 할인율
    @Column(nullable = false)
    private Integer discountPer;
    // 시간
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

    public ItemRead toRead() {
        return ItemRead.builder()
                .id(id)
                .name(name)
                .imgPath(imgPath)
                .price(price)
                .discountPer(discountPer)
                .build();
    }
}

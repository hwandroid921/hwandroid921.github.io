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
@Table(name = "unesco")
public class Unesco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "url_path1")
    private String urlPath1;
    @Column(name = "url_path2")
    private String urlPath2;
    @Column(name = "url_path3")
    private String urlPath3;
    @Column(name = "url_path4")
    private String urlPath4;
    @Column(name = "url_path5")
    private String urlPath5;
    @Column(name = "url_path6")
    private String urlPath6;
    @Column(name = "url_path7")
    private String urlPath7;
    @Column(name = "url_path8")
    private String urlPath8;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}

package com.tour.jeju.entity;

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
@Table(name = "tour_guide")
public class TourGuide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // 이미지 URL 목록 (최대 8개)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tour_guide_images", joinColumns = @JoinColumn(name = "tour_guide_id"))
    @Column(name = "img_url")
    @Builder.Default
    private List<String> imgUrls = new ArrayList<>();

    private int views;

    // 조회수 증가
    public void incrementViews() {
        this.views++;
    }

    // 게시글 수정
    public void edit(String title, String content, List<String> imgUrls) {
        if (title != null && !title.isEmpty()) this.title = title;
        if (content != null && !content.isEmpty()) this.content = content;
        if (imgUrls != null) {
            this.imgUrls.clear();
            this.imgUrls.addAll(imgUrls);
        }
    }

    // 작성자 확인
    public boolean isWriter(Long memberId) {
        return this.member.getId().equals(memberId);
    }

}

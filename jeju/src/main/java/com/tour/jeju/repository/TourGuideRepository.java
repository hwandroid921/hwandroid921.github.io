package com.tour.jeju.repository;

import com.tour.jeju.entity.TourGuide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourGuideRepository extends JpaRepository<TourGuide, Long> {

    // 회원별 게시글 수
    long countByMemberId(Long memberId);

    // 특정 회원이 쓴 글 조회
    Page<TourGuide> findByMemberId(Long memberId, Pageable pageable);

    // 제목으로 검색
    Page<TourGuide> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    // 내용으로 검색
    Page<TourGuide> findByContentContainingIgnoreCase(String keyword, Pageable pageable);

    // 제목 또는 내용으로 검색
    Page<TourGuide> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content, Pageable pageable);
}

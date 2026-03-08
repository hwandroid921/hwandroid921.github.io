package com.tour.jeju.repository;

import com.tour.jeju.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    // 제목으로 검색
    Page<Notice> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    // 내용으로 검색
    Page<Notice> findByContentContainingIgnoreCase(String keyword, Pageable pageable);

    // 제목 또는 내용으로 검색
    Page<Notice> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content, Pageable pageable);

    // 최근 게시글 3건
    List<Notice> findTop3ByOrderByCreatedAtDesc();
}

package com.tour.jeju.repository;

import com.tour.jeju.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    // 회원별 게시글 수
    long countByMemberId(Long memberId);
}

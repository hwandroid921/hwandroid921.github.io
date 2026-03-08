package com.tour.jeju.repository;

import com.tour.jeju.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByAttractionOrderByCreatedAtDesc(Long id, String pageable);

}

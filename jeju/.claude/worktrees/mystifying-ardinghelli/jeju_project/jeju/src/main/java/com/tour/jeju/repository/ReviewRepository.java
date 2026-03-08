package com.tour.jeju.repository;

import com.tour.jeju.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findTop5ByAttractionIdOrderByCreatedAtDesc(Long attractionId);
}

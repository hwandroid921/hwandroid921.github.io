package com.tour.jeju.repository;

import com.tour.jeju.entity.CommunityEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityEventRepository extends JpaRepository<CommunityEvent, Long> {
}

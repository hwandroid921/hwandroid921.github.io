package com.tour.jeju.repository;

import com.tour.jeju.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<Event, Long> {
    //평균 평점
    @Query("SELECT e.name FROM Event e WHERE e.name LIKE %:name%")
    String findByName(@Param("name") String name);
}
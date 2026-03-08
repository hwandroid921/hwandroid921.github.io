package com.tour.jeju.service;

import com.tour.jeju.repository.TourGuideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TourGuideService {

    private final TourGuideRepository tourGuideRepository;

}

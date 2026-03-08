package com.tour.jeju.service;

import com.tour.jeju.dto.AccommodationResponse;
import com.tour.jeju.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    @Transactional(readOnly = true)
    public List<AccommodationResponse> getByCategory(String category) {
        return accommodationRepository.findByCategory(category)
                .stream()
                .map(AccommodationResponse::fromEntity)
                .collect(Collectors.toList());
    }
}

package com.tour.jeju.service;

import com.tour.jeju.repository.CultureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CultureService {

    private final CultureRepository cultureRepository;

}

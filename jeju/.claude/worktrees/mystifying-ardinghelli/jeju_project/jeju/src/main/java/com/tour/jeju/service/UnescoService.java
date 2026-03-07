package com.tour.jeju.service;

import com.tour.jeju.repository.UnescoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnescoService {

    private final UnescoRepository unescoRepository;

}

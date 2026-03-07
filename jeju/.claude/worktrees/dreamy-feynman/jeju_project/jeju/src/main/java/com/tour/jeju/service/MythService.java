package com.tour.jeju.service;

import com.tour.jeju.repository.MythRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MythService {

    private final MythRepository mythRepository;

}

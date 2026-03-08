package com.tour.jeju.service;

import com.tour.jeju.dto.TourGuideResponse;
import com.tour.jeju.entity.TourGuide;
import com.tour.jeju.repository.MemberRepository;
import com.tour.jeju.repository.TourGuideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TourGuideService {

    private final TourGuideRepository tourGuideRepository;
    private final MemberRepository memberRepository;

    // 특정 회원이 작성한 게시글
    public Page<TourGuideResponse> getMyBoardList(Long memberId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<TourGuide> boardPage = tourGuideRepository.findByMemberId(memberId, pageable);
        return boardPage.map(TourGuideResponse::fromEntity);
    }


}

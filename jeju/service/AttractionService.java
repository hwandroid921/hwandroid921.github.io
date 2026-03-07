package com.tour.jeju.service;

import com.tour.jeju.dto.AttractionRequest;
import com.tour.jeju.dto.AttractionResponse;
import com.tour.jeju.entity.Attraction;
import com.tour.jeju.entity.Member;
import com.tour.jeju.repository.AttractionRepository;
import com.tour.jeju.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AttractionService {
    private final AttractionRepository attractionRepository;
    private final MemberRepository memberRepository;

    // 관광지 페이지 상세보기
    public AttractionResponse getAttraction(Long id) {
        Attraction attraction = attractionRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 페이지 입니다.")
        );
        return AttractionResponse.fromEntity(attraction);
    }
    // 관광지 추가
    @Transactional
    public Long insertAttraction(AttractionRequest request, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        Attraction attraction = request.toEntity(member);
        Attraction saveAttraction = attractionRepository.save(attraction);
        return saveAttraction.getId();
    }

    // 관광지 수정
    @Transactional
    public void editAttraction(Long id, AttractionRequest request) {
        Attraction attraction = attractionRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 관광지입니다.")
        );
        attraction.update(
                request.getName(),
                request.getAddress(),
                request.getTel(),
                request.getDescription(),
                request.getThumb(),
                request.getImgUrl1(), request.getImgUrl2(), request.getImgUrl3(), request.getImgUrl4(),
                request.getImgUrl5(), request.getImgUrl6(), request.getImgUrl7(), request.getImgUrl8(),
                request.getMapUrl(),
                request.getSlideUrl1(), request.getSlideUrl2(), request.getSlideUrl3(),
                request.getSlideUrl4(), request.getSlideUrl5()
        );
    }
    // 관광지 삭제
    public void deleteAttraction(Long id) {
        Attraction attraction = attractionRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 관광지 입니다.")
        );
        attractionRepository.deleteById(id);
    }
}

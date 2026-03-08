package com.tour.jeju.service;

import com.tour.jeju.config.FileConfig;
import com.tour.jeju.dto.AttractionRequest;
import com.tour.jeju.dto.AttractionResponse;
import com.tour.jeju.dto.ReviewResponse;
import com.tour.jeju.entity.Attraction;
import com.tour.jeju.repository.AttractionRepository;
import com.tour.jeju.repository.MemberRepository;
import com.tour.jeju.repository.ReviewRepository;
import com.tour.jeju.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttractionService {
    private final AttractionRepository attractionRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final FileConfig fileConfig;

    // 관광지 페이지 상세보기
    @Transactional(readOnly = true)
    public AttractionResponse getAttraction(Long id) {
        Attraction attraction = attractionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관광지입니다."));

        List<ReviewResponse> recentReviews = reviewRepository
                .findTop5ByAttractionIdOrderByCreatedAtDesc(id)
                .stream()
                .map(ReviewResponse::fromEntity)
                .collect(Collectors.toList());

        return AttractionResponse.fromEntity(attraction, recentReviews);
    }

    // 관광지 추가
    @Transactional
    public Long insertAttraction(AttractionRequest request,
                                 Long memberId,
                                 MultipartFile thumb) throws Exception {

        // 회원 검증
        memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // Request → Entity 변환 (toEntity 책임은 Request에)
        Attraction attraction = request.toEntity();

        // 썸네일 업로드
        if (thumb != null && !thumb.isEmpty()) {
            attraction.setThumb(FileUploadUtil.saveFile(fileConfig.uploadDir, thumb));
        }

        return attractionRepository.save(attraction).getId();
    }

    @Transactional
    public AttractionResponse editAttraction(Long id,
                                             AttractionRequest request,
                                             MultipartFile thumb) throws Exception {

        Attraction attraction = attractionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관광지입니다."));

        // 텍스트 필드 수정
        attraction.update(request);

        // 썸네일 수정
        if (thumb != null && !thumb.isEmpty()) {
            attraction.setThumb(FileUploadUtil.saveFile(fileConfig.uploadDir, thumb));
        }

        // ✅ @Transactional 환경에서 save() 생략 가능하나 명시적으로 유지
        return AttractionResponse.fromEntity(
                attractionRepository.save(attraction),
                reviewRepository.findTop5ByAttractionIdOrderByCreatedAtDesc(id)
                        .stream()
                        .map(ReviewResponse::fromEntity)
                        .collect(Collectors.toList())
        );
    }

    // 관광지 목록 (서브 탭 메뉴용 - 리뷰 제외)
    @Transactional(readOnly = true)
    public List<AttractionResponse> getAttractionList() {
        return attractionRepository.findAll()
                .stream()
                .map(a -> AttractionResponse.fromEntity(a, Collections.emptyList()))
                .collect(Collectors.toList());
    }

    // 관광지 삭제
    public void delete(Long id) {
        attractionRepository.deleteById(id);
    }
}
package com.tour.jeju.service;

import com.tour.jeju.config.FileConfig;
import com.tour.jeju.dto.ReviewRequest;
import com.tour.jeju.dto.ReviewResponse;
import com.tour.jeju.entity.Attraction;
import com.tour.jeju.entity.Member;
import com.tour.jeju.entity.Review;
import com.tour.jeju.repository.AttractionRepository;
import com.tour.jeju.repository.MemberRepository;
import com.tour.jeju.repository.ReviewRepository;
import com.tour.jeju.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final AttractionRepository attractionRepository;
    private final FileConfig fileConfig;

    // 리뷰 표시
    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewList(Long attractionId) {
        return reviewRepository.findTop5ByAttractionIdOrderByCreatedAtDesc(attractionId)
                .stream()
                .map(ReviewResponse::fromEntity)
                .collect(Collectors.toList());
    }
    // 리뷰 상세
    @Transactional(readOnly = true)
    public ReviewResponse getReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));
        return ReviewResponse.fromEntity(review);
    }

    // 리뷰 작성
    @Transactional
    public Long insertReview(Long attractionId,
                             Long memberId,
                             ReviewRequest request,
                             MultipartFile image) throws Exception {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Attraction attraction = attractionRepository.findById(attractionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관광지입니다."));

        Review review = request.toEntity(member, attraction);

        // imgUrl1만 사용
        if (image != null && !image.isEmpty()) {
            review.setImgUrl1(FileUploadUtil.saveFile(fileConfig.uploadDir, image));
        }

        return reviewRepository.save(review).getId();
    }

    // 리뷰 수정
    @Transactional
    public ReviewResponse editReview(Long id,
                                     Long memberId,
                                     ReviewRequest request,
                                     MultipartFile image) throws Exception {

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));

        // 작성자 본인 확인
        if (!review.getMember().getId().equals(memberId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        review.update(request);

        // 새 이미지가 있으면 교체, 없으면 기존 유지
        if (image != null && !image.isEmpty()) {
            review.setImgUrl1(FileUploadUtil.saveFile(fileConfig.uploadDir, image));
        }

        return ReviewResponse.fromEntity(reviewRepository.save(review));
    }

    // 리뷰 삭제
    @Transactional
    public void deleteReview(Long id, Long memberId) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));
        if (!review.getMember().getId().equals(memberId)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }
        reviewRepository.deleteById(id);
    }
}

package com.tour.jeju.service;

import com.tour.jeju.config.FileConfig;
import com.tour.jeju.dto.TourGuideRequest;
import com.tour.jeju.dto.TourGuideResponse;
import com.tour.jeju.entity.Member;
import com.tour.jeju.entity.TourGuide;
import com.tour.jeju.repository.MemberRepository;
import com.tour.jeju.repository.TourGuideRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourGuideService {

    private final TourGuideRepository tourGuideRepository;
    private final MemberRepository memberRepository;
    private final FileConfig fileConfig;

    // 이미지 파일 저장 (최대 8개)
    private List<String> saveImages(List<MultipartFile> images) {
        List<String> urls = new ArrayList<>();
        if (images == null || images.isEmpty()) return urls;

        String dirPath = fileConfig.uploadDir + "tourguide/";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (MultipartFile image : images) {
            if (image == null || image.isEmpty()) continue;
            String ext = StringUtils.getFilenameExtension(image.getOriginalFilename());
            String filename = UUID.randomUUID().toString() + (ext != null ? "." + ext : "");
            try {
                image.transferTo(new File(dirPath + filename));
                urls.add("/uploads/tourguide/" + filename);
                log.info("이미지 저장 완료: {}", filename);
            } catch (IOException e) {
                log.error("이미지 저장 실패: {}", e.getMessage());
            }
        }
        return urls;
    }

    // 여행후기 목록
    public Page<TourGuideResponse> getTravelogueList(int page, int size, String searchType, String keyword) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<TourGuide> boardPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            switch (searchType != null ? searchType : "") {
                case "title":
                    boardPage = tourGuideRepository.findByTitleContainingIgnoreCase(keyword, pageable);
                    break;
                case "content":
                    boardPage = tourGuideRepository.findByContentContainingIgnoreCase(keyword, pageable);
                    break;
                default:
                    boardPage = tourGuideRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword, pageable);
            }
        } else {
            boardPage = tourGuideRepository.findAll(pageable);
        }
        return boardPage.map(TourGuideResponse::fromEntity);
    }

    // 여행후기 상세 (조회수 증가)
    @Transactional
    public TourGuideResponse getTravelogue(Long id) {
        TourGuide tourGuide = tourGuideRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        tourGuide.incrementViews();
        log.info("여행후기 조회 - ID: {}, 조회수: {}", tourGuide.getId(), tourGuide.getViews());
        return TourGuideResponse.fromEntity(tourGuide);
    }

    // 여행후기 상세 - 수정용 (조회수 증가 없음, 작성자 검증)
    @Transactional(readOnly = true)
    public TourGuideResponse getTravelogueForEdit(Long id, Long memberId) {
        TourGuide tourGuide = tourGuideRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        if (!tourGuide.isWriter(memberId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        return TourGuideResponse.fromEntity(tourGuide);
    }

    // 여행후기 작성 (이미지 업로드 포함)
    @Transactional
    public Long writeTravelogue(TourGuideRequest request, Long memberId, List<MultipartFile> images) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        List<String> imgUrls = saveImages(images);
        TourGuide tourGuide = request.toEntity(member, imgUrls);
        TourGuide saved = tourGuideRepository.save(tourGuide);
        log.info("여행후기 작성 완료 - ID: {}, 작성자: {}, 이미지: {}장", saved.getId(), member.getName(), imgUrls.size());
        return saved.getId();
    }

    // 여행후기 수정 (기존 이미지 유지 + 신규 이미지 추가)
    @Transactional
    public void editTravelogue(Long id, TourGuideRequest request, Long memberId,
                               List<String> keepImages, List<MultipartFile> newImages) {
        TourGuide tourGuide = tourGuideRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        if (!tourGuide.isWriter(memberId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        // 유지할 기존 이미지 + 새로 업로드한 이미지 합산 (최대 8개)
        List<String> finalImgUrls = new ArrayList<>();
        if (keepImages != null) finalImgUrls.addAll(keepImages);

        int remaining = 8 - finalImgUrls.size();
        if (remaining > 0 && newImages != null && !newImages.isEmpty()) {
            List<MultipartFile> toUpload = newImages.size() <= remaining ? newImages : newImages.subList(0, remaining);
            finalImgUrls.addAll(saveImages(toUpload));
        }

        tourGuide.edit(request.getTitle(), request.getContent(), finalImgUrls);
        log.info("여행후기 수정 완료 - ID: {}, 이미지: {}장", id, finalImgUrls.size());
    }

    // 여행후기 삭제
    @Transactional
    public void deleteTravelogue(Long id, Long memberId) {
        TourGuide tourGuide = tourGuideRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        if (!tourGuide.isWriter(memberId)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }
        tourGuideRepository.delete(tourGuide);
        log.info("여행후기 삭제 완료 - ID: {}", id);
    }

    // 특정 회원이 작성한 게시글 (마이페이지용)
    public Page<TourGuideResponse> getMyBoardList(Long memberId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<TourGuide> boardPage = tourGuideRepository.findByMemberId(memberId, pageable);
        return boardPage.map(TourGuideResponse::fromEntity);
    }
}

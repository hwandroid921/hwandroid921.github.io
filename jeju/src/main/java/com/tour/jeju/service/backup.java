//package com.tour.jeju.service;
//
//import com.tour.jeju.config.FileConfig;
//import com.tour.jeju.dto.AttractionRequest;
//import com.tour.jeju.dto.AttractionResponse;
//import com.tour.jeju.entity.Attraction;
//import com.tour.jeju.entity.Member;
//import com.tour.jeju.repository.AttractionRepository;
//import com.tour.jeju.repository.MemberRepository;
//import com.tour.jeju.util.FileUploadUtil;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class AttractionService {
//    private final AttractionRepository attractionRepository;
//    private final MemberRepository memberRepository;
//    private final FileConfig fileConfig;
//
//    private static final int MAX_IMG = 8;
//    private static final int MAX_SLIDE = 5;
//
//    // 관광지 페이지 상세보기
//    public AttractionResponse getAttraction(Long id) {
//        Attraction attraction = attractionRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 페이지 입니다.")
//        );
//        return AttractionResponse.fromEntity(attraction);
//    }
//
//    // 관광지 추가
//    @Transactional
//    public Long insertAttraction(AttractionRequest request,
//                                 MultipartFile thumb,
//                                 List<MultipartFile> images,
//                                 List<MultipartFile> slides) throws Exception {
//        // 멤버아이디 예외 처리
//        Attraction attraction = request.toEntity();
//
//        if (thumb != null && !thumb.isEmpty()) {
//            attraction.setThumb(
//                    FileUploadUtil.saveFile(fileConfig.uploadDir, thumb)
//            );
//        }
//
//        if (images != null && !images.isEmpty()) {
//            if (images.size() > MAX_IMG)
//                throw new IllegalArgumentException("이미지는 최대 8개까지 등록 가능합니다.");
//            attraction.setImageUrls(uploadFiles(images, MAX_IMG));
//        }
//
//        if (slides != null && !slides.isEmpty()) {
//            if (slides.size() > MAX_SLIDE)
//                throw new IllegalArgumentException("슬라이드는 최대 5개까지 등록 가능합니다.");
//            attraction.setSlideUrls(uploadFiles(slides, MAX_SLIDE));
//        }
//
//        return AttractionResponse.fromEntity(attractionRepository.save(attraction));
//    }
//
//    // 관광지 수정
//    @Transactional
//    public AttractionResponse editAttraction(Long id,
//                                             AttractionRequest request,
//                                             MultipartFile thumb,
//                                             List<MultipartFile> images,
//                                             List<MultipartFile> slides) throws Exception {
//        Attraction attraction = attractionRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("관광지를 찾을 수 없습니다."));
//
//        attraction.update(request);
//
//
//        if (thumb != null && !thumb.isEmpty()) {
//            attraction.setThumb(
//                    FileUploadUtil.saveFile(fileConfig.uploadDir, thumb)
//            );
//        }
//
//        if (images != null && !images.isEmpty()) {
//            attraction.setImageUrls(
//                    mergeWithExisting(attraction.getImageUrls(), images, MAX_IMG)
//            );
//        }
//
//        if (slides != null && !slides.isEmpty()) {
//            attraction.setSlideUrls(
//                    mergeWithExisting(attraction.getSlideUrls(), slides, MAX_SLIDE)
//            );
//        }
//
//        return AttractionResponse.fromEntity(attractionRepository.save(attraction));
//    }
//
//    public AttractionResponse getOne(Long id) {
//        return AttractionResponse.fromEntity(
//                attractionRepository.findById(id)
//                        .orElseThrow(() -> new EntityNotFoundException("관광지를 찾을 수 없습니다."))
//        );
//    }
//
//    public List<AttractionResponse> getList() {
//        return attractionRepository.findAll()
//                .stream()
//                .map(AttractionResponse::fromEntity)
//                .collect(Collectors.toList());
//    }
//
//    // 관광지 삭제
//    public void delete(Long id) {
//        attractionRepository.deleteById(id);
//    }
//
//    // 파일 리스트 업로드 → String 배열 반환
//    private String[] uploadFiles(List<MultipartFile> files, int maxSize) throws Exception {
//        String[] urls = new String[maxSize];
//        for (int i = 0; i < files.size(); i++) {
//            MultipartFile file = files.get(i);
//            if (file != null && !file.isEmpty()) {
//                urls[i] = FileUploadUtil.saveFile(fileConfig.uploadDir, file);
//            }
//        }
//        return urls;
//    }
//
//    private String[] mergeWithExisting(List<String> existing,
//                                       List<MultipartFile> newFiles,
//                                       int maxSize) throws Exception {
//        String[] urls = new String[maxSize];
//
//        for (int i = 0; i < existing.size() && i < maxSize; i++) {
//            urls[i] = existing.get(i);
//        }
//        for (int i = 0; i < newFiles.size() && i < maxSize; i++) {
//            MultipartFile file = newFiles.get(i);
//            if (file != null && !file.isEmpty()) {
//                urls[i] = FileUploadUtil.saveFile(fileConfig.uploadDir, file);
//            }
//        }
//        return urls;
//    }
//}
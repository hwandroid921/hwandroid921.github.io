package com.tour.jeju.service;

import com.tour.jeju.dto.NoticeResponse;
import com.tour.jeju.entity.Notice;
import com.tour.jeju.repository.MemberRepository;
import com.tour.jeju.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    // 공지사항 목록
    public Page<NoticeResponse> getNoticeList(int page, int size, String searchType, String keyword) {
        Page<Notice> noticePage;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        if (keyword != null && !keyword.trim().isEmpty()) {
            switch (searchType) {
                case "title" :
                    noticePage = noticeRepository.findByTitleContainingIgnoreCase(keyword, pageable);
                    break;
                case "content" :
                    noticePage = noticeRepository.findByContentContainingIgnoreCase(keyword, pageable);
                    break;
                case "all" :
                    noticePage = noticeRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword, pageable);
                    break;
                default:
                    noticePage = noticeRepository.findAll(pageable);
            }
        } else {
            noticePage = noticeRepository.findAll(pageable);
        }
        return noticePage.map(NoticeResponse::fromEntity);
    }

    // 게시글 상세 보기 (조회수 증가)
    @Transactional
    public NoticeResponse getNotice(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        return NoticeResponse.fromEntity(notice);
    }

}

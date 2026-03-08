package com.tour.jeju.service;

import com.tour.jeju.dto.NoticeRequest;
import com.tour.jeju.dto.NoticeResponse;
import com.tour.jeju.entity.Member;
import com.tour.jeju.entity.Notice;
import com.tour.jeju.repository.MemberRepository;
import com.tour.jeju.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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
        notice.incrementViews();
        log.info("게시글 조회 - ID : {}, 조회수 : {}", notice.getId(), notice.getViews());
        return NoticeResponse.fromEntity(notice);
    }

    // 게시글 상세 보기 (조회수 증가되지 않음) => 작성자 본인이 상세보기 한 경우
    @Transactional
    public NoticeResponse getNotice(Long id, Long memberId) {
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        if (!notice.isWriter(memberId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        return NoticeResponse.fromEntity(notice);
    }

    // 공지사항 작성
    public Long insertNotice(NoticeRequest request, Long adminId) {
        Member member = memberRepository.findById(adminId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        Notice notice = request.toEntity(member);
        Notice saveNotice = noticeRepository.save(notice);
        log.info("공지사항 작성 완료 - ID: {}, 작성자: {}", saveNotice.getId(), member.getId());

        return saveNotice.getId();
    }

    // 공지사항 수정
    @Transactional
    public void editNotice(Long id, NoticeRequest request, Long adminId) {
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );
        if (!notice.isWriter(adminId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        notice.editNotice(request.getTitle(), request.getContent());

        log.info("공지사항 수정 완료: {}", id);
    }

    // 공지사항 삭제
    @Transactional
    public void deleteNotice(Long id, Long adminId) {
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );
        if (!notice.isWriter(adminId)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }
        noticeRepository.delete(notice);
        log.info("공지사항 삭제 완료: {}", id);
    }

    //최근 게시글 5건
    @Transactional(readOnly = true)
    public List<NoticeResponse> getLatestBoards() {
        return noticeRepository.findTop3ByOrderByCreatedAtDesc()
                .stream()
                .map(NoticeResponse::fromEntity)
                .toList();
    }

}

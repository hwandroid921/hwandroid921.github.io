package com.tour.jeju.service;

import com.tour.jeju.dto.MemberJoinRequest;
import com.tour.jeju.dto.MemberLoginRequest;
import com.tour.jeju.dto.MemberResponse;
import com.tour.jeju.dto.MemberUpdateRequest;
import com.tour.jeju.entity.Member;
import com.tour.jeju.repository.ComplaintRepository;
import com.tour.jeju.repository.MemberRepository;
import com.tour.jeju.repository.TourGuideRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final TourGuideRepository tourGuideRepository;
    private final ComplaintRepository complaintRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public Long join(MemberJoinRequest request) {
        // 아이디 중복 검증
        if (memberRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 사용중인 아이디 입니다.");
        }
        // 비밀번호와 비밀번호확인이 일치하는지 검증
        if (!request.isPasswordMatch()) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        // DTO를 Entity로 변환
        Member member = request.toEntity();
        // 비밀번호를 암호화 한 비밀번호로 저장
        member.setPassword(encodedPassword);
        //
        Member saveMember = memberRepository.save(member);
        log.info("회원 가입 완료: ID {}, Username: {}",
                saveMember.getId(),
                saveMember.getUsername());

        return saveMember.getId();
    }

    // 로그인
    public MemberResponse login(MemberLoginRequest request) {

        Member member = memberRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")
        );

        // 단방향 암호화
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        log.info("로그인 성공 - Username: {}", member.getUsername());

        return MemberResponse.fromEntity(member);   // Entity
    }

    // 관리자를 위한 회원 목록
    @Transactional
    public Page<MemberResponse> getMemberList(int page, int size, String searchType, String keyword) {

        Page<Member> memberPage;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        if (keyword != null && !keyword.trim().isEmpty()) {
            switch (searchType) {
                case "username" :
                    memberPage = memberRepository.findByUsernameContainingIgnoreCase(keyword, pageable);
                    break;
                case "name" :
                    memberPage = memberRepository.findByNameContainingIgnoreCase(keyword, pageable);
                    break;
                case "all" :
                    memberPage = memberRepository.findByNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(keyword, keyword, pageable);
                    break;
                default:
                    memberPage = memberRepository.findAll(pageable);
            }
        } else {
            memberPage = memberRepository.findAll(pageable);
        }

        return memberPage.map(member -> {
            long tourGuideCount = tourGuideRepository.countByMemberId(member.getId());
            return MemberResponse.fromEntity(member);
        });
    }

    // 회원 상세 조회
    public MemberResponse getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        long tourGuideCount = tourGuideRepository.countByMemberId(member.getId());

        return MemberResponse.fromEntity(member, tourGuideCount);
    }

    // 회원 정보 변경
    @Transactional
    public void updateMember(Long id, MemberUpdateRequest request) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 아이디 입니다.")
        );
        if (!request.isPasswordMatch()) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        member.updateInfo(encodedPassword, request.getName());
        log.info("회원 정보 수정 완료: ID{}", id);
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        memberRepository.delete(member);
        log.info("회원 탈퇴 완료 - ID: {}, username: {},", id, member.getUsername());
    }

    public boolean checkUsernameDuplicate(String username) {

        return memberRepository.existsByUsername(username);
    }

    //최근 7일내 가입한 회원 목록
    @Transactional(readOnly = true)
    public List<MemberResponse> getLatestMembers() {

        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);

        return memberRepository.findByCreatedAtAfterOrderByCreatedAtDesc(sevenDaysAgo)
                .stream()
                .map(member -> {
                    long tourGuideCount = tourGuideRepository.countByMemberId(member.getId());

                    // 이름 마스킹 처리
                    String maskedName = maskName(member.getName());

                    return MemberResponse.builder()
                            .id(member.getId())
                            .username(member.getUsername())
                            .name(maskedName)
                            .createdAt(member.getCreatedAt())
                            .tourGuideCount(tourGuideCount)
                            .build();
                })
                .toList();
    }

    //이름 마스킹하기
    private String maskName(String name) {

        if (name.length() <= 2) {
            return name.charAt(0) + "*";
        }

        return name.substring(0, 1) +
                "*".repeat(name.length() - 2) +
                name.substring(name.length() - 1);

    }

}

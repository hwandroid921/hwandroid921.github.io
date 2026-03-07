package com.tour.jeju.service;

import com.tour.jeju.dto.MemberJoinRequest;
import com.tour.jeju.entity.Member;
import com.tour.jeju.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
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

}

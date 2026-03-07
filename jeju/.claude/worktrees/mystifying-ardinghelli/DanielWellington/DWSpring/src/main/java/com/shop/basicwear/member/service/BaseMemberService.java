package com.shop.basicwear.member.service;

import com.shop.basicwear.member.entity.Member;
import com.shop.basicwear.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseMemberService implements MemberService{
    private final MemberRepository memberRepository;

    @Override
    public void save(String name, String loginId, String loginPw) {
        memberRepository.save(new Member(name, loginId, loginPw));
    }

    @Override
    public Member find(String loginId, String loginPw) {
        Optional<Member> memberOptional = memberRepository.findByLoginIdAndLoginPw(loginId,loginPw);
        return memberOptional.orElse(null);
    }
}

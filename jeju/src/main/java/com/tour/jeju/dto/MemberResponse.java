package com.tour.jeju.dto;

import com.tour.jeju.entity.Member;
import com.tour.jeju.entity.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponse {
    private Long id;
    private String username;
    private String name;
    private String phone;
    private LocalDateTime createdAt;
    private long tourGuideCount;
    private Role role;

    // Entity -> DTO
    // 게시글이 없는 회원
    public static MemberResponse fromEntity(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .name(member.getName())
                .phone(member.getPhone())
                .createdAt(member.getCreatedAt())
                .tourGuideCount(0)
                .build();
    }
    // 게시글이 있는 회원
    public static MemberResponse fromEntity(Member member, long boardCount) {
        return MemberResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .name(member.getName())
                .phone(member.getPhone())
                .createdAt(member.getCreatedAt())
                .tourGuideCount(0)
                .build();
    }
}

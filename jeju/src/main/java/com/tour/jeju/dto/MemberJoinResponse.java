package com.tour.jeju.dto;

import com.tour.jeju.entity.Member;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MemberJoinResponse {

    private Long id;
    private String username;
    private String name;

    public static MemberJoinResponse fromEntity(Member member) {
        return MemberJoinResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .name(member.getName())
                .build();
    }
}

package com.tour.jeju.dto;

import com.tour.jeju.entity.Member;
import com.tour.jeju.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MemberJoinRequest {

    @NotBlank(message = "아이디는 필수 입력 항목입니다.")
    @Size(max = 50)
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    @Size(max = 50)
    private String name;

    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .username(username)
                .password(encodedPassword)
                .name(name)
                .role(Role.USER)
                .build();
    }
}

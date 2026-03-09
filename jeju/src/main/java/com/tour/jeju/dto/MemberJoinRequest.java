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

    @NotBlank(message = "비밀번호 확인은 필수 입력 항목입니다.")
    private String passwordConfirm;

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    @Size(max = 50)
    private String name;

    private String phone;

    private Role role = Role.USER;

    // 비밀번호랑 비밀번호 확인이 일치하는지
    public boolean isPasswordMatch() {
        return password != null && password.equals(passwordConfirm);
    }

    public Member toEntity(String encodedPassword) {

        if (username != null && username.startsWith("admin")) {
            role = Role.ADMIN;
        }
        return Member.builder()
                .username(username)
                .password(encodedPassword)
                .name(name)
                .phone(phone)
                .role(role)
                .build();
    }
}

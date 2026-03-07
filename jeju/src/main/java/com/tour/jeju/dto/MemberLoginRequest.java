package com.tour.jeju.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLoginRequest {

    @NotBlank(message = "아이디를 입력하세요")
    private String username;

    @NotBlank(message = "비밀번호 입력하세요")
    private String password;
}

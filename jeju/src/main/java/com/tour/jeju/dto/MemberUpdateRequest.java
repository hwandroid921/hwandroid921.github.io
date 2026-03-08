package com.tour.jeju.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpdateRequest {

    @NotBlank(message = "비밀번호는 필수 입니다.")
    @Size(min = 4, max = 20, message = "비밀번호는 4~20자 이내로 입력하세요.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수 입력입니다.")
    private String passwordConfirm;

    @NotBlank(message = "이름은 필수 입력입니다.")
    @Size(min = 2, max = 20, message = "이름은 2~20자 이내로 입력하세요.")
    private String name;

    // 비밀번호랑 비밀번호 확인이 일치하는지
    public boolean isPasswordMatch() {

        return password != null && password.equals(passwordConfirm);
    }
}

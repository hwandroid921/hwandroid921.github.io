package com.tour.jeju.dto;

import com.tour.jeju.entity.Member;
import com.tour.jeju.entity.Notice;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NoticeRequest {

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    @Size(min = 2, max = 200, message = "제목은 2~200자 이내로 입력해주세요")
    private String title;

    @NotBlank(message = "내용은 필수 입력 항목입니다.")
    @Size(min = 2, max = 200, message = "제목은 2~200자 이내로 입력해주세요")
    private String content;

    private boolean pinned;

    public Notice toEntity(Member member) {
        return Notice.builder()
                .title(title)
                .content(content)
                .member(member)
                .pinned(pinned)
                .build();
    }
}

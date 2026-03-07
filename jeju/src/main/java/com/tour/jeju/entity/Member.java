package com.tour.jeju.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 15)
    private String phone;

    @CreationTimestamp  // 현재 시간을 값으로 채워서 쿼리를 생성
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void changePassword(String encodePassword) {

        this.password = password;
    }

    // 회원 정보를 수정하기 위해 비밀번호와 이름을 받음
    public void updateInfo (String password, String name) {
        // 비밀번호와 이름이 null이거나 비어있지 않으면 비밀변호를 변경
        if (password != null && !password.isEmpty()) {
            this.password = password;
        }
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

}

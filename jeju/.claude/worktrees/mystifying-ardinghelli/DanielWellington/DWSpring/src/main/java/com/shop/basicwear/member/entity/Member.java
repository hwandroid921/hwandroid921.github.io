package com.shop.basicwear.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50, updatable = true)
    private String loginId;
    @Column(nullable = false, length = 100)
    private String loginPw;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private String created;

    public Member(String name, String loginId, String loginPw) {
        this.name = name;
        this.loginId = loginId;
        this.loginPw = loginPw;
    }
}

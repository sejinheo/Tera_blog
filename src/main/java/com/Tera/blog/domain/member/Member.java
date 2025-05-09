package com.Tera.blog.domain.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, unique = true, length = 30)
    private String loginId;

    @Column(nullable = false)
    private String password;

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    @Builder
    public Member(String email, String loginId, String password) {
        this.email = email;
        this.loginId = loginId;
        this.password = password;
    }
}

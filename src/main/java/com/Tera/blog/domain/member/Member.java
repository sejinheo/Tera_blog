package com.Tera.blog.domain.member;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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
    //JPA는 기본 생성자가 반드시 필요
    protected Member() {
    }
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
    public Member(String email, String loginId, String password) {
        this.email = email;
        this.loginId = loginId;
        this.password = password;
    }
}

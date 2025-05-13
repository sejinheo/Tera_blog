package com.Tera.blog.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public record MemberSignUpRequestDto (
    @NotBlank
    @Email
    String email,

    @NotBlank
    @Size(min = 6, message = "비밀번호는 6자 이상이어야 합니다.")
    String password,

    @NotBlank
    String loginId
){}

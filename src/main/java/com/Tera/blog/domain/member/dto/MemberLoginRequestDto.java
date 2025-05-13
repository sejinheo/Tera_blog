package com.Tera.blog.domain.member.dto;

import jakarta.validation.constraints.NotBlank;

public record MemberLoginRequestDto(
    @NotBlank
    String password,
    @NotBlank
    String loginId
){}

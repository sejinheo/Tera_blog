package com.Tera.blog.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberLoginRequestDto {
    @NotBlank
    private String password;
    @NotBlank
    private String loginId;
}

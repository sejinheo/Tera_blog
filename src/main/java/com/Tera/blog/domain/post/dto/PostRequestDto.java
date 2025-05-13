package com.Tera.blog.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequestDto (
    @NotBlank(message = "제목을 써주세요.")
    @Size(min = 1, message = "제목 1글자 이상 써주세요.")
    String title,

    @NotBlank(message = "내용을 써주세요.")
    @Size(min = 1, message = "내용 1글자 이상 써주세요.")
    String content
){}

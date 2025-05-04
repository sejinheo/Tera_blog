package com.Tera.blog.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostRequestDto {
    @NotBlank(message = "제목을 써주세요.")
    private String title;
    @NotBlank(message = "내용을 써주세요.")
    private String content;
}

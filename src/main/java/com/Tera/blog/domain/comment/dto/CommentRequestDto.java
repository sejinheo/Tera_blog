package com.Tera.blog.domain.comment.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @Size(min = 1, message = "내용 1글자 이상 써주세요.")
    private String content;
}

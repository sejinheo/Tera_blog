package com.Tera.blog.domain.comment.dto;

import com.Tera.blog.domain.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final String content;
    private final String member;

    public CommentResponseDto(Comment comment) {
        this.content = comment.getContent();
        this.member = comment.getMember().getLoginId();
    }
}

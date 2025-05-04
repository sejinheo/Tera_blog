package com.Tera.blog.domain.post.dto;

import com.Tera.blog.domain.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;

    @Getter
    public class PostResponseDto {
        private Long id;
        private String title;
        private String content;
        private String authorLoginId;
        private LocalDateTime createdAt;

        public PostResponseDto(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.authorLoginId = post.getMember().getLoginId();
            this.createdAt = post.getCreatedAt();
        }
    }

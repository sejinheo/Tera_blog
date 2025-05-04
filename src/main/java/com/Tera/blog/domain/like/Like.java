package com.Tera.blog.domain.like;

import com.Tera.blog.domain.member.Member;
import com.Tera.blog.domain.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "heart")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Post post;

    public Like(Member member, Post post) {
        this.member = member;
        this.post = post;
    }
}

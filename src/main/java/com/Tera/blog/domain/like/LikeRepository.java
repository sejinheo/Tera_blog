package com.Tera.blog.domain.like;

import com.Tera.blog.domain.member.Member;
import com.Tera.blog.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByMemberAndPost(Member member, Post post);
    long countByPost(Post post);
    void deleteByMemberAndPost(Member member, Post post);
}

package com.Tera.blog.domain.comment;

import com.Tera.blog.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
 List<Comment> findByPost(Post post);
}

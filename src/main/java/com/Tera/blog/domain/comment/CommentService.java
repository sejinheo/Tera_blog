package com.Tera.blog.domain.comment;

import com.Tera.blog.domain.comment.dto.CommentResponseDto;
import com.Tera.blog.domain.member.AlreadyExistException;
import com.Tera.blog.domain.member.Member;
import com.Tera.blog.domain.post.Post;
import com.Tera.blog.domain.post.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long createComment(String content,Long postId, Member member) {
        Post post = postRepository.findById(postId).
                orElseThrow(()->new IllegalArgumentException("게시글 없어요"));
        Comment comment = new Comment(member, post,content);
        return commentRepository.save(comment).getId();
    }
    @Transactional
    public List<CommentResponseDto> getComments(Long postId){
        Post post = postRepository.findById(postId).
                orElseThrow(()->new IllegalArgumentException("게시글이 없어요ㅠ"));
        return commentRepository.findByPost(post).stream()
                .map(CommentResponseDto::new)
                .toList();
    }

    @Transactional
    public void updateComment(Long commentId, String content, Member member) {
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(()->new IllegalArgumentException("댓글 없음"));
        if(!comment.getMember().getId().equals(member.getId()))
            throw new AlreadyExistException("작성자만 수정할 수 있음");

        comment.updateContent(content);
    }

    @Transactional
    public void deleteComment(Long commentId, Member member) {
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(()->new IllegalArgumentException("댓글 없음"));
        if(!comment.getMember().getId().equals(member.getId()))
            throw new AlreadyExistException("작성자만 삭제 가능");
       commentRepository.delete(comment);
    }
}

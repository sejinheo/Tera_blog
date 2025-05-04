package com.Tera.blog.domain.comment;

import com.Tera.blog.config.jwt.UserDetailsImpl;
import com.Tera.blog.domain.comment.dto.CommentRequestDto;
import com.Tera.blog.domain.comment.dto.CommentResponseDto;
import com.Tera.blog.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}")
    public Long createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto dto,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(dto.getContent(),postId,userDetails.getMember());
    }
    @GetMapping("/{postId}")
    public List<CommentResponseDto> getComments(@PathVariable Long postId){
        return commentService.getComments(postId);
    }
    @PutMapping("/{commentId}")
    public void updateComment(@PathVariable Long commentId,
                              @RequestBody CommentRequestDto dto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.updateComment(commentId,dto.getContent(),userDetails.getMember());
    }
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.deleteComment(commentId,userDetails.getMember());
    }
}

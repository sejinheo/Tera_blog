package com.Tera.blog.domain.like;

import com.Tera.blog.config.jwt.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;
    @PostMapping("/{postId}/toggle")
    public String toggleLike(@AuthenticationPrincipal UserDetailsImpl userDetails,
                             @PathVariable Long postId) {
        Long memberId = userDetails.getMemberId();
        boolean liked = likeService.toggleLike(memberId, postId);
        return liked ? "좋아요" : "좋아요 취소";
    }
    @GetMapping("/{postId}/count")
    public long GetLikeCount(@PathVariable Long postId){
        return likeService.getLikeCount(postId);
    }

}

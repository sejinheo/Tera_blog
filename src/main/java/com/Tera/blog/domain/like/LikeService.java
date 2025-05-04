package com.Tera.blog.domain.like;

import com.Tera.blog.domain.member.Member;
import com.Tera.blog.domain.member.MemberRepository;
import com.Tera.blog.domain.post.Post;
import com.Tera.blog.domain.post.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public boolean toggleLike(Long memberId, Long postId) {
        Member member = memberRepository.findById(memberId).
                orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자"));
        Post post = postRepository.findById(postId).
                orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글"));

        Optional<Like> existingLike = likeRepository.findByMemberAndPost(member, post);

        if(existingLike.isPresent()){
            likeRepository.delete(existingLike.get());
            return false;
        }
        else{
            Like like = new Like(member,post);
            likeRepository.save(like);
            return true;
        }

    }

    public Long getLikeCount(Long postId) {
        Post post = postRepository.findById(postId).
                orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글"));
        return likeRepository.countByPost(post);
    }
}

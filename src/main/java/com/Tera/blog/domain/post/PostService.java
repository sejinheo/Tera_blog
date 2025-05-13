package com.Tera.blog.domain.post;

import com.Tera.blog.domain.member.Member;
import com.Tera.blog.domain.post.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@DynamicUpdate
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    public void create(PostRequestDto Dto, Member member) {
        Post post = new Post(Dto.title(),Dto.content(), member);
        postRepository.save(post);
    }

    public void update(Long postId,PostRequestDto Dto, Member member) {
        Post post = postRepository.findById(postId).orElseThrow(()->new NoSuchElementException("게시글 없음"));

        if(!post.getMember().getId().equals(member.getId())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
       if(Dto.title() != null && !Dto.title().isBlank()) {
           log.info("타이틀 업데이트 호출됨");
           post.updateTitle(Dto.title());
       }
       if(Dto.content() != null && !Dto.content().isBlank()) {
           log.info("콘텐츠 업데이트 호출됨");
           post.updateContent(Dto.content());
       }
       log.info("PostService.update() 호출 - 제목: {}, 내용: {}", Dto.title(), Dto.content());
        postRepository.save(post);
    }

    public void delete(Long postId, Member member) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("게시글 없음"));

        if (!post.getMember().getId().equals(member.getId())) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
        postRepository.delete(post);
    }

    public List<Post> readAll() {
        return postRepository.findAll();
    }
}

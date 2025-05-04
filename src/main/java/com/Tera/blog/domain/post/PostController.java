package com.Tera.blog.domain.post;

import com.Tera.blog.config.jwt.UserDetailsImpl;
import com.Tera.blog.domain.post.dto.PostRequestDto;
import com.Tera.blog.domain.post.dto.PostResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor// final 필드나 @NonNull 필드를 자동으로 생성자에 포함시켜주는 역할
@RequestMapping("api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public Long createPost(@Valid @RequestBody PostRequestDto dto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.create(dto.getTitle(),dto.getContent(), userDetails.getMember());
    }


    @GetMapping
    public List<PostResponseDto> readAllPosts() {
        return postService.readAll().stream()
                .map(PostResponseDto::new)
                .toList();
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable Long id,
                           @RequestBody PostRequestDto dto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.update(id, dto.getTitle(), dto.getContent(), userDetails.getMember());
    }
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id,
                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.delete(id, userDetails.getMember());
    }

}

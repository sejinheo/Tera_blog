package com.Tera.blog.domain.member;

import com.Tera.blog.domain.member.dto.MemberLoginRequestDto;
import com.Tera.blog.domain.member.dto.MemberSignUpRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody MemberSignUpRequestDto memberSignUpRequestDto){
        Long id = memberService.signUp(memberSignUpRequestDto);
        return ResponseEntity.ok("회원가입 성공! ID: " + id);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody MemberLoginRequestDto memberLoginRequestDto){
        String token = memberService.login(memberLoginRequestDto);
        return ResponseEntity.ok("로그인 성공! 토큰: " + token);
    }
}

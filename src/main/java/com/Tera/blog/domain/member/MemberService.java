package com.Tera.blog.domain.member;

import com.Tera.blog.config.jwt.JwtUtil;
import com.Tera.blog.domain.member.dto.MemberLoginRequestDto;
import com.Tera.blog.domain.member.dto.MemberSignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//final 필드를 자동으로 생성자에 주입
@RequiredArgsConstructor
public class MemberService {
   private final MemberRepository memberRepository;
   private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

   public void signUp(MemberSignUpRequestDto signUpRequestDto) {

       if(memberRepository.findByEmail(signUpRequestDto.getEmail()).isPresent()) {
           throw new AlreadyExistException("이미 등록된 이메일 입니다.");
       }
       if(memberRepository.findByLoginId(signUpRequestDto.getLoginId()).isPresent()) {
           throw new AlreadyExistException("이미 사용중인 아이디 입니다.");
       }
       String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
       Member member = Member.builder()
               .email(signUpRequestDto.getEmail())
               .loginId(signUpRequestDto.getLoginId())
               .password(encodedPassword)
               .build();

       memberRepository.save(member).getId();
   }

    public String login(MemberLoginRequestDto loginDto) {
        Member member = memberRepository.findByLoginId(loginDto.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

      if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 여기서 JWT 토큰 발급 등의 로직 추가 가능
        return jwtUtil.generateToken(member.getLoginId());
    }
}

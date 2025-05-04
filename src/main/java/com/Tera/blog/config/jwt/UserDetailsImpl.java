package com.Tera.blog.config.jwt;

import com.Tera.blog.domain.member.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
//JWT 인증 후 Authentication 객체에 저장되는 사용자 정보
public class UserDetailsImpl implements UserDetails {
    private final Member member;

    public UserDetailsImpl(Member member) {
        this.member = member;
    }
    public Long getMemberId() {
        return member.getId();
    }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return Collections.emptyList(); }//권한 정보 반환
    @Override public String getPassword() { return member.getPassword(); }
    @Override public String getUsername() { return member.getLoginId(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}

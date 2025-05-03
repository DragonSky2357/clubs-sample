package com.example.Clubs.config.security.service;

import com.example.Clubs.config.security.entity.User;
import com.example.Clubs.member.entity.Member;
import com.example.Clubs.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 의존성 주입 (예시로 JPA 사용)
    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 데이터베이스에서 사용자 정보 조회 (여기서는 예시로 UserRepository 사용)
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // UserDetails 객체를 반환 (Spring Security가 요구하는 형태)
        return User.builder()
                .id(member.getId())
                .email(member.getEmail())
                .password(member.getPassword()) // 비밀번호는 암호화된 상태여야 함
                .role(User.Role.USER) // 권한 부여
                .member(member)
                .build();
    }
}

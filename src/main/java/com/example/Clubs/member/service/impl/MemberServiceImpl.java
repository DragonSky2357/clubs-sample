package com.example.Clubs.member.service.impl;

import com.example.Clubs.member.dto.request.CreateMemberRequest;
import com.example.Clubs.member.dto.response.CreateMemberResponse;
import com.example.Clubs.member.entity.Member;
import com.example.Clubs.member.repository.MemberRepository;
import com.example.Clubs.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateMemberResponse createMember(CreateMemberRequest request) {
        Member member = request.toEntity();

        String encryptPassword = passwordEncoder.encode(request.getPassword());
        member.setPassword(encryptPassword);

        Member saveMember = memberRepository.save(member);

        return CreateMemberResponse.from(saveMember);
    }

    @Override
    public Optional<Member> getMemberById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Member> getAllMembers() {
        return List.of();
    }

    @Override
    public Member updateMember(Long id, Member member) {
        return null;
    }

    @Override
    public void deleteMember(Long id) {

    }
}

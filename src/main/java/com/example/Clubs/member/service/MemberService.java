package com.example.Clubs.member.service;

import com.example.Clubs.member.dto.request.CreateMemberRequest;
import com.example.Clubs.member.dto.response.CreateMemberResponse;
import com.example.Clubs.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    // 회원 생성 (Create)
    CreateMemberResponse createMember(CreateMemberRequest request);

    // 회원 조회 (Read)
    Optional<Member> getMemberById(Long id);
    List<Member> getAllMembers();

    // 회원 정보 수정 (Update)
    Member updateMember(Long id, Member member);

    // 회원 삭제 (Delete)
    void deleteMember(Long id);
}
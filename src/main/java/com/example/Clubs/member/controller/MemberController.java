package com.example.Clubs.member.controller;

import com.example.Clubs.member.dto.request.CreateMemberRequest;
import com.example.Clubs.member.dto.response.CreateMemberResponse;
import com.example.Clubs.member.entity.Member;
import com.example.Clubs.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/v1/signup")
    public CreateMemberResponse createMember(@RequestBody CreateMemberRequest request){
        return memberService.createMember(request);
    }
}

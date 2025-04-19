package com.example.Clubs.member.dto.response;

import com.example.Clubs.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateMemberResponse {
    private long id;
    private String username;
    private String email;

    public static CreateMemberResponse from(Member member){
        return CreateMemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .username(member.getUsername())
                .build();
    }
}

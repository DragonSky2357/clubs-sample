package com.example.Clubs.member.dto.request;

import com.example.Clubs.member.entity.Member;
import lombok.Getter;

@Getter
public class CreateMemberRequest {
    private String username;
    private String email;
    private String password;

    public Member toEntity() {
        return Member.builder()
                .username(this.username)
                .email(this.email)
                .build();
    }
}

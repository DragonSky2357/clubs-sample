package com.example.Clubs.club.dto.request;

import com.example.Clubs.club.entity.Club;
import com.example.Clubs.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateClubRequest {

    private String title;
    private String description;

    public Club toEntity(Member member){
        return Club.builder()
                .owner(member)
                .title(this.title)
                .description(this.description)
                .build();
    }
}

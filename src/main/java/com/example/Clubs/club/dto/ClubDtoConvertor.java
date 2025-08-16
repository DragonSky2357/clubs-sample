package com.example.Clubs.club.dto;

import com.example.Clubs.club.dto.request.CreateClubRequest;
import com.example.Clubs.club.dto.response.ClubResponse;
import com.example.Clubs.club.entity.Club;
import com.example.Clubs.member.entity.Member;

public class ClubDtoConvertor {

    public static ClubResponse entityToResponseDto(Club entity) {
        return ClubResponse.builder()
                .clubId(entity.getId())
                .clubTitle(entity.getTitle())
                .clubDescription(entity.getDescription())
                .clubOwnerUserName(entity.getOwner().getUsername())
                .build();
    }

    public static Club CreateClubRequestToEntity(CreateClubRequest request, Member member) {
        return Club.builder()
                .owner(member)
                .title(request.getTitle())
                .description(request.getDescription())
                .isActive(true)
                .build();
    }

}

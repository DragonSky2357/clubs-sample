package com.example.Clubs.club.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ClubResponse {

    private long clubId;
    private String title;
    private String description;
    private String ownerUserName;

}

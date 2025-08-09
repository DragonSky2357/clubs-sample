package com.example.Clubs.club.dto.request;

import com.example.Clubs.club.entity.Club;
import com.example.Clubs.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateClubRequest {

    @NotBlank(message = "title은 비어있을 수 없습니다.")
    private String title;
    @NotBlank(message = "description은 비어있을 수 없습니다.")
    private String description;

}

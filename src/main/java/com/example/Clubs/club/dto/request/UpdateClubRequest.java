package com.example.Clubs.club.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
public class UpdateClubRequest {

    @NotBlank(message = "title은 비어있을 수 없습니다.")
    private String title;
    @NotBlank(message = "description은 비어있을 수 없습니다.")
    private String description;
}


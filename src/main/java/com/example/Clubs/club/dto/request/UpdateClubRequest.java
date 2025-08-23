package com.example.Clubs.club.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateClubRequest {

    @Max(value = 100, message = "title의 최대 길이는 100자 입니다.")
    @Min(value = 2, message = "title은 2자 이상이어야 합니다.")
    @NotBlank(message = "title은 비어있을 수 없습니다.")
    private String title;

    @Max(value = 300, message = "description의 최대 길이는 300자 입니다.")
    private String description;
}


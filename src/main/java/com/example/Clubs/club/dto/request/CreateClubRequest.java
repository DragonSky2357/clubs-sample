package com.example.Clubs.club.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateClubRequest {

    @Size(min = 2, max = 100, message = "title은 2자 이상, 100자 이하로 입력해야 합니다.")
    @NotBlank(message = "title은 비어있을 수 없습니다.")
    private String title;

    @Size(max = 300, message = "description은 최대 300자까지 입력 가능합니다.")
    private String description;
}

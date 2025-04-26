package com.example.Clubs.post.dto.request;

import com.example.Clubs.member.entity.Member;
import com.example.Clubs.post.entity.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreatePostRequest {
    @NotBlank(message = "제목은 비어 있을 수 없습니다.")
    @Size(max = 100, message = "제목은 100자 이내로 입력해주세요.")
    private String title;
    private String content;

    public Post toEntity(Member member) {
        return Post.builder()
                .title(title)
                .content(content)
                .isActive(true)
                .member(member)
                .build();
    }
}

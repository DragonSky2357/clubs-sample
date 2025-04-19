package com.example.Clubs.post.dto.request;

import com.example.Clubs.member.entity.Member;
import com.example.Clubs.post.entity.Post;
import lombok.Getter;

@Getter
public class CreatePostRequest {
    private String title;
    private String content;

    public Post toEntity(Member member){
        return Post.builder()
                .title(title)
                .content(content)
                .isActive(true)
                .member(member)
                .build();
    }
}

package com.example.Clubs.post.dto.response;

import com.example.Clubs.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetPostResponse {
    private long postId;
    private String title;
    private String content;
    private String writer;

    public static GetPostResponse from(Post findPost) {
        return GetPostResponse.builder()
                .title(findPost.getTitle())
                .content(findPost.getContent())
                .writer(findPost.getMember().getUsername())
                .postId(findPost.getId())
                .build();
    }
}

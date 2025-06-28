package com.example.Clubs.post.dto.response;

import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetPostResponse {
    private long postId;           // 게시물 ID
    private String postTitle;      // 제목
    private String postContent;    // 내용
    private String postCreatedAt;  // 작성일 (YYYY-MM-DD 형식)
    private String postWriter;     // 작성자

    public static GetPostResponse from(Post findPost) {
        return GetPostResponse.builder()
                .postTitle(findPost.getTitle())
                .postContent(findPost.getContent())
                .postCreatedAt(findPost.getMember().getUsername())
//                .postWriter(findPost.getId())
                .build();
    }
}

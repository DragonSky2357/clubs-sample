package com.example.Clubs.comment.dto.response;

import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class  CommentResponse {
    private long id;
    private CommentType type;
    private String content;
    private long target;
    private Long parent;
    private long writerId;
    private String writerName;

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .type(comment.getType())
                .content(comment.getContent())
                .target(comment.getTarget())
                .parent(comment.getParent())
                .writerId(comment.getMember().getId())
                .writerName(comment.getMember().getUsername())
                .build();
    }
}
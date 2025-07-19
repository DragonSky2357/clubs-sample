package com.example.Clubs.comment.dto.request;

import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import com.example.Clubs.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
    private String content;
    private long target;
    private Long parent;
    private CommentType type;

    public Comment toEntity(Member member) {
        return Comment.builder()
                .content(content)
                .target(target)
                .parent(parent)
                .type(type)
                .member(member)
                .isActive(true)
                .build();
    }
}
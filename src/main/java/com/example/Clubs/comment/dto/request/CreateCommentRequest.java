package com.example.Clubs.comment.dto.request;

import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import com.example.Clubs.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
    @NotBlank(message = "댓글 내용은 필수입니다.")
    private String content;

    @NotNull(message = "대상 게시물 ID는 필수입니다.")
    private Long target;

    private Long parent; // 부모 댓글 ID (대댓글의 경우)

    @NotNull(message = "댓글 유형은 필수입니다.")
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
package com.example.Clubs.comment.dto.request;

import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import com.example.Clubs.config.security.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {
    @NotBlank(message = "댓글은 비어 있을 수 없습니다.")
    @Size(max = 200, message = "댓글은 200자 이내로 입력해주세요.")
    private String content;

    @NotNull(message = "댓글 유형은 필수입니다.")
    private CommentType type;

    @NotNull(message = "댓글 게시물을 지정해주세요.")
    private long target;

    private Long parent;

    public Comment toEntity(long commentId,User member) {
        return Comment.builder()
                .id(commentId)
                .content(content)
                .member(member.getMember())
                .type(type)
                .target(target)
                .partent(parent)
                .isActive(true)
                .build();
    }
}

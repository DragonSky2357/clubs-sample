package com.example.Clubs.comment.dto.request;

import com.example.Clubs.comment.entity.CommentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentRequest {
    @NotNull(message = "댓글 ID는 필수입니다.")
    private Long commentId;

    @NotBlank(message = "댓글 내용은 필수입니다.")
    private String content;

    public void setCommentType(CommentType type) {
    }
}

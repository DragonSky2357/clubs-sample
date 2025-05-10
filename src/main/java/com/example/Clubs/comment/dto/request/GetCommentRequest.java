package com.example.Clubs.comment.dto.request;

import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import com.example.Clubs.config.security.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class GetCommentRequest {
    @NotNull(message = "댓글 유형은 필수입니다.")
    private CommentType type;

    @NotNull(message = "댓글 게시물을 지정해주세요.")
    private long target;
}

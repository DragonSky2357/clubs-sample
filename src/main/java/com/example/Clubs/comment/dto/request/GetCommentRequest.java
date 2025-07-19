package com.example.Clubs.comment.dto.request;

import com.example.Clubs.comment.entity.CommentType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCommentRequest {
    @NotNull(message = "댓글 유형은 필수입니다.")
    private CommentType type;

    @NotNull(message = "댓글 게시물을 지정해주세요.")
    private long target;
}

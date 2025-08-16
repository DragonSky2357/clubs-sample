package com.example.Clubs.comment.dto.request;

import com.example.Clubs.comment.entity.CommentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCommentRequest {
    @NotNull(message = "댓글 유형은 필수입니다.")
    private CommentType type;

    @NotNull(message = "댓글 게시물을 지정해주세요.")
    private long target;

    @NotBlank(message = "댓글 내용은 필수입니다")
    @Size(max = 1000, message = "댓글은 1000자를 초과할 수 없습니다")

    // 페이징을 위한 필드(설정 변경 가능)
    private int page = 0;
    private int size = 20;
}

package com.example.Clubs.comment.exception;

import com.example.Clubs.common.global.CommonException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum CommentErrorCode implements CommonException {
    COMMENT_NOTFOUND_ERROR(HttpStatus.BAD_REQUEST,2010,"존재하지 않은 댓글입니다."),
    COMMENT_PERMISSION_ERROR(HttpStatus.UNAUTHORIZED,2020,"권한이 없습니다.");

    private final HttpStatus status;
    private final int errorCode;
    private final String message;

    @Override
    public int getStatus() {
        return status.value();
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

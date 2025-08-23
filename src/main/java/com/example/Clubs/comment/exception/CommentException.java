package com.example.Clubs.comment.exception;

import com.example.Clubs.common.global.BusinessException;
import com.example.Clubs.common.global.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CommentException extends BusinessException {
    private final CommentErrorCode errorCode;

    protected CommentException(ErrorCode errorCode, CommentErrorCode errorCode1) {
        super(errorCode);
        this.errorCode = errorCode1;
    }
}

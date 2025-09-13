package com.example.Clubs.post.exception;

import com.example.Clubs.common.global.BusinessException;
import com.example.Clubs.common.global.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostException extends BusinessException {
    private final PostErrorCode errorCode;

    protected PostException(ErrorCode errorCode, PostErrorCode errorCode1) {
        super(errorCode);
        this.errorCode = errorCode1;
    }
}

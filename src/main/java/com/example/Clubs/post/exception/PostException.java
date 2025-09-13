package com.example.Clubs.post.exception;

import com.example.Clubs.common.global.BusinessException;
import com.example.Clubs.common.global.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class PostException extends BusinessException {
    public PostException(ErrorCode errorCode) {
        super(errorCode);
    }
}

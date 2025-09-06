package com.example.Clubs.comment.exception;

import com.example.Clubs.common.global.CustomBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CommentException extends CustomBaseException {
    private final CommentErrorCode errorCode;

    // 기본 생성자
    public CommentException(CommentErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    // 상세 메시지 포함한 생성자 - 디버깅용
    public CommentException(CommentErrorCode errorCode, String detailMessage) {
        super(errorCode.getMessage() + " - " + detailMessage);
        this.errorCode = errorCode;
    }

    // 원인 예외 포함한 생성자 - 에러 체인용
    public CommentException(CommentErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

}
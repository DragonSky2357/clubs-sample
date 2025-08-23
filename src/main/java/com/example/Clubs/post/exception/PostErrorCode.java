package com.example.Clubs.post.exception;

import com.example.Clubs.common.global.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum PostErrorCode implements ErrorCode {
    POST_CREATE_ERROR(HttpStatus.BAD_REQUEST,2000,"게시물 생성 오류입니다."),
    POST_NOTFOUND_ERROR(HttpStatus.BAD_REQUEST,2010,"존재하지 않은 게시물입니다."),
    POST_PERMISSION_ERROR(HttpStatus.UNAUTHORIZED,2020,"권한이 없습니다.");

    private final HttpStatus status;
    private final int errorCode;
    private final String message;


    @Override
    public int getCode() {return status.value();}

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

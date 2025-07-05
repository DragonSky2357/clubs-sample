package com.example.Clubs.member.exception;

import com.example.Clubs.common.global.CommonException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum MemberErrorCode implements CommonException {

    MEMBER_CREATE_ERROR(HttpStatus.BAD_REQUEST,2000,"유저 생성 오류입니다."),
    MEMBER_NOTFOUND_ERROR(HttpStatus.BAD_REQUEST,2010,"존재하지 않은 유저입니다."),
    MEMBER_PERMISSION_ERROR(HttpStatus.UNAUTHORIZED,2020,"권한이 없습니다.");

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



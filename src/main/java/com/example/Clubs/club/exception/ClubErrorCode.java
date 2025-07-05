package com.example.Clubs.club.exception;

import com.example.Clubs.common.global.CommonException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ClubErrorCode implements CommonException {

    CLUB_CREATE_ERROR(HttpStatus.BAD_REQUEST,2000,"클럽 생성 오류입니다."),
    CLUB_NOTFOUND_ERROR(HttpStatus.BAD_REQUEST,2010,"존재하지 않은 클럽입니다."),
    CLUB_PERMISSION_ERROR(HttpStatus.UNAUTHORIZED,2020,"권한이 없습니다.");

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

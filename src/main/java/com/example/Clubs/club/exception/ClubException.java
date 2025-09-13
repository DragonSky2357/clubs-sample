package com.example.Clubs.club.exception;

import com.example.Clubs.common.global.BusinessException;
import com.example.Clubs.common.global.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ClubException extends BusinessException {

    private final ClubErrorCode errorCode;

    protected ClubException(ErrorCode errorCode, ClubErrorCode errorCode1) {
        super(errorCode);
        this.errorCode = errorCode1;
    }
}

package com.example.Clubs.club.exception;

import com.example.Clubs.common.global.CommonException;
import com.example.Clubs.common.global.CustomBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClubException extends CustomBaseException {
    private final ClubErrorCode errorCode;

    public ClubException(CommonException exception, ClubErrorCode errorCode) {
        super(exception);
        this.errorCode = errorCode;
    }
}

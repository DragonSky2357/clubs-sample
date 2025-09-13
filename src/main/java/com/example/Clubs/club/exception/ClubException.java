package com.example.Clubs.club.exception;

import com.example.Clubs.common.global.BusinessException;
import com.example.Clubs.common.global.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ClubException extends BusinessException {
    public ClubException(ErrorCode errorCode) {
        super(errorCode);
    }
}

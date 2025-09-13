package com.example.Clubs.member.exception;

import com.example.Clubs.common.global.BusinessException;
import com.example.Clubs.common.global.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class MemberException extends BusinessException {
    private final MemberErrorCode errorCode;

    protected MemberException(ErrorCode errorCode, MemberErrorCode errorCode1) {
        super(errorCode);
        this.errorCode = errorCode1;
    }
}

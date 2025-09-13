package com.example.Clubs.member.exception;

import com.example.Clubs.common.global.BusinessException;
import com.example.Clubs.common.global.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class MemberException extends BusinessException {
    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}

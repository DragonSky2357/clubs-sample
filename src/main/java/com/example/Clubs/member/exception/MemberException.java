package com.example.Clubs.member.exception;

import com.example.Clubs.common.global.CommonException;
import com.example.Clubs.common.global.CustomBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberException extends CustomBaseException {
    private final MemberErrorCode errorCode;
}

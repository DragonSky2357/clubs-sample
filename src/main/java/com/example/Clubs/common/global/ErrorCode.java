package com.example.Clubs.common.global;

public interface ErrorCode {
    int getCode(); // 상태값
    String getErrorCode(); // 에러코드
    String getMessage(); // 메시지
}

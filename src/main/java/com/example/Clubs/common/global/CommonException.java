package com.example.Clubs.common.global;

public interface CommonException {
    int getStatus(); // 상태값
    int getErrorCode(); // 에러코드
    String getMessage(); // 메시지
}

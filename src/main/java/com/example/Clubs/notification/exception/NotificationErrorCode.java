package com.example.Clubs.notification.exception;

import com.example.Clubs.common.global.ErrorCode;
import org.springframework.http.HttpStatus;

public enum NotificationErrorCode implements ErrorCode {

    NOTIFICATION_NOT_FOUND("NOTIFICATION_001", "존재하지 않는 알림입니다.",HttpStatus.NOT_FOUND),
    UNAUTHORIZED_NOTIFICATION_ACCESS("NOTIFICATION_002", "알림에 접근할 권한이 없습니다.", HttpStatus.FORBIDDEN),
    UNAUTHORIZED_NOTIFICATION_REMOVE("NOTIFICATION_003", "알림을 삭제할 권한이 없습니다.", HttpStatus.FORBIDDEN),
    INVALID_NOTIFICATION_CONTENT("NOTIFICATION_004", "알림 내용이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOTIFICATION_ALREADY_DELETE("NOTIFICATION_005", "이미 삭제된 알림입니다.", HttpStatus.NOT_FOUND),
    NOTIFICATION_OPERATION_FAILED("NOTIFICATION_999", "알림 처리 중 오류가 발생 했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);



    private final String errorCode;
    private final String message;
    private final HttpStatus status;

    NotificationErrorCode(String errorCode, String message, HttpStatus status) {
        this.errorCode = errorCode;
        this.message = message;
        this.status = status;
    }

    @Override
    public int getErrorCode() {return errorCode;}

    @Override
    public String getMessage() {return message;}

    @Override
    public int getCode() {return status.value();}

}

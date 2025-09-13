package com.example.Clubs.common.global;

public abstract class CustomBaseException extends RuntimeException {
    public CustomBaseException(String message) {
        super(message);
    }

    public CustomBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract ErrorCode getErrorCode();
}

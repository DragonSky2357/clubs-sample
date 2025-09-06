package com.example.Clubs.common.global;

public abstract class CustomBaseException extends RuntimeException {
    public CustomBaseException() {
        super();
    }
    public CustomBaseException(CommonException exception) {
        super(exception.getMessage());
    }

    public CustomBaseException(String message) {}

    public CustomBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract CommonException getErrorCode();
}
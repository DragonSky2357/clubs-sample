package com.example.Clubs.common.global;

public abstract class CustomBaseException extends RuntimeException {
    public CustomBaseException() {
        super();
    }
    public CustomBaseException(CommonException exception) {
        super(exception.getMessage());
    }
    public abstract CommonException getErrorCode();
}
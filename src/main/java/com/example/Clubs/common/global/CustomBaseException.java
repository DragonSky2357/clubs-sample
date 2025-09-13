package com.example.Clubs.common.global;

public abstract class CustomBaseException extends RuntimeException {
    public abstract CommonException getErrorCode();
}

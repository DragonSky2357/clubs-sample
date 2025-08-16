package com.example.Clubs.comment.exception;

import com.example.Clubs.common.global.CustomBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentException extends CustomBaseException {
    private final CommentErrorCode errorCode;
}
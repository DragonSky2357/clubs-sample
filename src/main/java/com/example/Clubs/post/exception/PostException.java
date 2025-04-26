package com.example.Clubs.post.exception;

import com.example.Clubs.common.global.CustomBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostException extends CustomBaseException {
    private final PostErrorCode errorCode;
}

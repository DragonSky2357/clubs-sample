package com.example.Clubs.common.global;

import com.example.Clubs.post.exception.PostErrorCode;
import com.example.Clubs.post.exception.PostException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CustomBaseException.class)
    public ResponseEntity handlePostException(PostException postException){
        PostErrorCode errorCode = postException.getErrorCode();

        return ResponseEntity.status(errorCode.getStatus())
                .body(commonExceptionResponse(postException.getErrorCode()));
    }



    private Map<String, Object> commonExceptionResponse(CommonException commonException) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", commonException.getStatus());
        response.put("errorCode", commonException.getErrorCode());
        response.put("message", commonException.getMessage());
        return response;
    }
}
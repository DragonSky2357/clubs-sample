package com.example.Clubs.comment.exception;

import com.example.Clubs.common.global.CommonException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum CommentErrorCode implements CommonException {
    // 댓글 조회 관련 에러
    COMMENT_NOTFOUND_ERROR(HttpStatus.BAD_REQUEST,2010,"존재하지 않은 댓글입니다."),

    // 권한 관련 에러
    COMMENT_PERMISSION_ERROR(HttpStatus.UNAUTHORIZED,2020,"댓글에 대한 권한이 없습니다."),

    // 요청 데이터 검증 에러
    COMMENT_REQUEST_NULL_ERROR(HttpStatus.BAD_REQUEST, 2030, "댓글 요청 데이터가 없습니다."),
    COMMENT_CONTENT_EMPTY_ERROR(HttpStatus.BAD_REQUEST, 2031, "댓글 내용을 입력해주세요."),
    COMMENT_TYPE_NULL_ERROR(HttpStatus.BAD_REQUEST, 2032, "댓글 유형을 선택해주세요."),
    COMMENT_ID_INVALID_ERROR(HttpStatus.BAD_REQUEST, 2033, "유효하지 않은 댓글 ID입니다."),
    COMMENT_TARGET_INVALID_ERROR(HttpStatus.BAD_REQUEST, 2034, "유효하지 않은 대상 게시물입니다."),

    // 멤버 관련 에러
    MEMBER_NULL_ERROR(HttpStatus.UNAUTHORIZED, 2040, "로그인이 필요합니다."),

    // 댓글 상태 관련 에러
    COMMENT_ALREADY_DELETED_ERROR(HttpStatus.BAD_REQUEST, 2050, "이미 삭제된 댓글입니다."),
    COMMENT_PARENT_NOTFOUND_ERROR(HttpStatus.BAD_REQUEST, 2051, "존재하지 않는 부모 댓글입니다."),

    // 서버 에러
    COMMENT_CREATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 2060, "댓글 생성 중 오류가 발생했습니다."),
    COMMENT_UPDATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 2061, "댓글 수정 중 오류가 발생했습니다."),
    COMMENT_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 2062, "댓글 삭제 중 오류가 발생했습니다.");


    private final HttpStatus status;
    private final int errorCode;
    private final String message;

    @Override
    public int getStatus() {
        return status.value();
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

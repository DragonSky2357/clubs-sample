package com.example.Clubs.comment.exception;

import com.example.Clubs.common.global.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements CommonException {
    // 댓글 조회 관련 에러 (2010번대)
    COMMENT_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, 2010, "존재하지 않는 댓글입니다."),
    COMMENT_ALREADY_DELETED_ERROR(HttpStatus.BAD_REQUEST, 2011, "이미 삭제된 댓글입니다."),

    // 댓글 권한 관련 에러 (2020번대)
    COMMENT_PERMISSION_DENIED_ERROR(HttpStatus.FORBIDDEN, 2020, "댓글에 대한 권한이 없습니다."),
    COMMENT_AUTHOR_ONLY_ERROR(HttpStatus.FORBIDDEN, 2021, "댓글 작성자만 수정/삭제할 수 있습니다."),
    COMMENT_ADMIN_ONLY_ERROR(HttpStatus.FORBIDDEN, 2022, "관리자만 접근할 수 있습니다."),

    // 댓글 작성 관련 에러 (2030번대)
    COMMENT_CONTENT_EMPTY_ERROR(HttpStatus.BAD_REQUEST, 2030, "댓글 내용을 입력해주세요."),
    COMMENT_CONTENT_TOO_LONG_ERROR(HttpStatus.BAD_REQUEST, 2031, "댓글은 최대 1000자까지 입력 가능합니다."),
    COMMENT_PARENT_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST, 2032, "부모 댓글이 존재하지 않습니다."),
    COMMENT_DEPTH_LIMIT_EXCEEDED_ERROR(HttpStatus.BAD_REQUEST, 2033, "대댓글은 최대 2단계까지만 가능합니다."),
    COMMENT_SELF_REPLY_ERROR(HttpStatus.BAD_REQUEST, 2034, "자신의 댓글에는 답글을 달 수 없습니다."),

    // 댓글 수정 관련 에러 (2040번대)
    COMMENT_EDIT_TIME_EXPIRED_ERROR(HttpStatus.BAD_REQUEST, 2040, "댓글 수정 가능 시간이 만료되었습니다."),
    COMMENT_CANNOT_EDIT_DELETED_ERROR(HttpStatus.BAD_REQUEST, 2041, "삭제된 댓글은 수정할 수 없습니다."),

    // 댓글 삭제 관련 에러 (2050번대)
    COMMENT_HAS_REPLIES_ERROR(HttpStatus.BAD_REQUEST, 2050, "답글이 있는 댓글은 삭제할 수 없습니다."),
    COMMENT_ALREADY_DELETED_BY_USER_ERROR(HttpStatus.BAD_REQUEST, 2051, "이미 사용자에 의해 삭제된 댓글입니다."),
    COMMENT_ALREADY_DELETED_BY_ADMIN_ERROR(HttpStatus.BAD_REQUEST, 2052, "이미 관리자에 의해 삭제된 댓글입니다."),

    // 게시물 관련 에러 (2060번대)
    POST_NOT_FOUND_FOR_COMMENT_ERROR(HttpStatus.BAD_REQUEST, 2060, "댓글을 달 게시물이 존재하지 않습니다."),
    POST_COMMENT_DISABLED_ERROR(HttpStatus.BAD_REQUEST, 2061, "댓글이 비활성화된 게시물입니다."),
    POST_ARCHIVED_ERROR(HttpStatus.BAD_REQUEST, 2062, "보관된 게시물에는 댓글을 달 수 없습니다."),

    // 사용자 관련 에러 (2070번대)
    USER_NOT_FOUND_FOR_COMMENT_ERROR(HttpStatus.BAD_REQUEST, 2070, "댓글 작성자 정보를 찾을 수 없습니다."),
    USER_BLOCKED_FROM_COMMENT_ERROR(HttpStatus.FORBIDDEN, 2071, "댓글 작성이 제한된 사용자입니다."),
    USER_NOT_AUTHENTICATED_ERROR(HttpStatus.UNAUTHORIZED, 2072, "로그인이 필요합니다."),

    // 스팸/제재 관련 에러 (2080번대)
    COMMENT_SPAM_DETECTED_ERROR(HttpStatus.BAD_REQUEST, 2080, "스팸으로 의심되는 댓글입니다."),
    COMMENT_RATE_LIMIT_EXCEEDED_ERROR(HttpStatus.TOO_MANY_REQUESTS, 2081, "댓글 작성 속도 제한에 걸렸습니다."),
    COMMENT_BANNED_WORDS_ERROR(HttpStatus.BAD_REQUEST, 2082, "금지된 단어가 포함되어 있습니다."),

    // 시스템 에러 (2090번대)
    COMMENT_DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 2090, "댓글 처리 중 데이터베이스 오류가 발생했습니다."),
    COMMENT_NOTIFICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 2091, "댓글 알림 발송 중 오류가 발생했습니다."),
    COMMENT_CACHE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 2092, "댓글 캐시 처리 중 오류가 발생했습니다.");

    private final HttpStatus status;
    private final int errorCode;
    private final String message;


    @Override
    public int getCode() {return status.value();}

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

package com.example.Clubs.comment.service.impl;

import com.example.Clubs.comment.dto.request.CreateCommentRequest;
import com.example.Clubs.comment.dto.request.GetCommentRequest;
import com.example.Clubs.comment.dto.request.UpdateCommentRequest;
import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import com.example.Clubs.comment.exception.CommentErrorCode;
import com.example.Clubs.comment.exception.CommentException;
import com.example.Clubs.comment.repository.CommentRepository;
import com.example.Clubs.comment.service.CommentService;
import com.example.Clubs.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private static final int MAX_COMMENT_LENGTH = 1000;

    @Override
    @Transactional
    public Comment createComment(CreateCommentRequest request, Member member) {
        log.debug("Creating comment for member: {}, target: {}, type: {}",
                member.getId(), request.getTarget(), request.getType());

        validateCreateCommentRequest(request, member);

        try {
            Comment newComment = request.toEntity(member);
            commentRepository.save(newComment);

            log.info("Comment created successfully for member: {}", member.getId());
            return newComment;
        } catch (DataAccessException e) {
            log.error("Database error while creating comment for member: {}", member.getId(), e);
            throw new CommentException(CommentErrorCode.COMMENT_DATABASE_ERROR, e);
        }
    }

    @Override
    public Comment getComment(long commentId) {
        return findComment(commentId);
    }

    @Override
    public Comment getCommentByIdAndType(long commentId, CommentType commentType) {
        validateCommentIdAndType(commentId, commentType);
        return findCommentByCommentType(commentId, commentType);
    }

    @Override
    public List<Comment> getComments(GetCommentRequest request) {
        validateGetCommentRequest(request);

        log.debug("Fetching comments for target: {}, type: {}",
                request.getTarget(), request.getType());

        try {
            return commentRepository.findByTargetAndTypeOrderByCreatedAtDesc(request.getTarget(), request.getType());
        } catch (DataAccessException e) {
            log.error("Database error while fetching comments for target: {}, type: {}",
                    request.getTarget(), request.getType(), e);
            throw new CommentException(CommentErrorCode.COMMENT_DATABASE_ERROR, e);
        }
    }

    @Override
    @Transactional
    public void updateComment(UpdateCommentRequest request, Member member) {
        validateUpdateCommentRequest(request, member);

        Comment comment = findComment(request.getCommentId());
        validateCommentOwnership(comment, member);

        // 삭제된 댓글 수정 방지
        if (comment.isDeleted()) {
            throw new CommentException(CommentErrorCode.COMMENT_CANNOT_EDIT_DELETED_ERROR,
                    "댓글 ID: " + comment.getId());
        }

        String previousContent = comment.getContent();

        try {
            comment.updateContent(request.getContent());

            log.info("Comment updated. ID: {}, Type: {}, Member: {}",
                    comment.getId(), comment.getType(), member.getId());
            log.debug("Content changed from: '{}' to: '{}'", previousContent, request.getContent());
        } catch (DataAccessException e) {
            log.error("Database error while updating comment ID: {}", comment.getId(), e);
            throw new CommentException(CommentErrorCode.COMMENT_DATABASE_ERROR, e);
        }
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId, CommentType commentType, Member member) {
        validateCommentIdAndType(commentId, commentType);
        validateMember(member);

        Comment comment = findComment(commentId, commentType);
        validateCommentOwnership(comment, member);

        // 이미 삭제된 댓글 체크
        if (comment.isDeleted()) {
            throw new CommentException(CommentErrorCode.COMMENT_ALREADY_DELETED_ERROR,
                    "댓글 ID: " + commentId);
        }

        try {
            // 소프트 삭제 수행
            comment.delete();

            log.info("Comment soft deleted. ID: {}, Type: {}, Member: {}",
                    commentId, commentType, member.getId());
        } catch (DataAccessException e) {
            log.error("Database error while deleting comment ID: {}", commentId, e);
            throw new CommentException(CommentErrorCode.COMMENT_DATABASE_ERROR, e);
        }
    }

    private Comment findComment(long commentId) {
        validateCommentId(commentId);

        return commentRepository.findById(commentId)
                .orElseThrow(() -> {
                    log.warn("Comment not found with CommentId: {}", commentId);
                    return new CommentException(CommentErrorCode.COMMENT_NOT_FOUND_ERROR,
                            "댓글 ID: " + commentId);
                });
    }

    private Comment findComment(long commentId, CommentType commentType) {
        return commentRepository.findByIdAndType(commentId, commentType)
                .orElseThrow(() -> {
                    log.warn("Comment not found with ID: {} and Type: {}", commentId, commentType);
                    return new CommentException(CommentErrorCode.COMMENT_NOT_FOUND_ERROR,
                            "댓글 ID: " + commentId + ", 타입: " + commentType);
                });
    }

    private Comment findCommentByCommentType(long commentId, CommentType commentType) {
        return commentRepository.findByIdAndType(commentId, commentType)
                .orElseThrow(() -> {
                    log.warn("Comment not found with ID: {} and Type: {}", commentId, commentType);
                    return new CommentException(CommentErrorCode.COMMENT_NOT_FOUND_ERROR,
                            "댓글 ID: " + commentId + ", 타입: " + commentType);
                });
    }

    private void validateCreateCommentRequest(CreateCommentRequest request, Member member) {
        if (request == null) {
            throw new IllegalArgumentException("댓글 수정 요청이 null입니다.");
        }

        validateMember(member);

        // 댓글 내용 검증
        if (!StringUtils.hasText(request.getContent())) {
            throw new CommentException(CommentErrorCode.COMMENT_CONTENT_EMPTY_ERROR);
        }

        // 댓글 길이 검증
        if (request.getContent().length() > MAX_COMMENT_LENGTH) {
            throw new CommentException(CommentErrorCode.COMMENT_CONTENT_TOO_LONG_ERROR,
                    "현재 길이: " + request.getContent().length() + "자, 최대 허용: " + MAX_COMMENT_LENGTH + "자");
        }

        // 댓글 타입 검증
        if (request.getType() == null) {
            throw new IllegalArgumentException("댓글 타입이 null입니다.");
        }
    }

    private void validateUpdateCommentRequest(UpdateCommentRequest request, Member member) {
        if (request == null) {
            throw new IllegalArgumentException("댓글 수정 요청이 null입니다.");
        }

        validateMember(member);
        validateCommentId(request.getCommentId());

        // 댓글 내용 검증
        if (!StringUtils.hasText(request.getContent())) {
            throw new CommentException(CommentErrorCode.COMMENT_CONTENT_EMPTY_ERROR);
        }

        // 댓글 길이 검증
        if (request.getContent().length() > MAX_COMMENT_LENGTH) {
            throw new CommentException(CommentErrorCode.COMMENT_CONTENT_TOO_LONG_ERROR,
                    "현재 길이: " + request.getContent().length() + "자, 최대 허용: " + MAX_COMMENT_LENGTH + "자");
        }
    }

    private void validateGetCommentRequest(GetCommentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("댓글 조회 요청이 null입니다.");
        }

        if (request.getType() == null) {
            throw new IllegalArgumentException("댓글 타입이 null입니다.");
        }
    }

    private void validateCommentIdAndType(long commentId, CommentType commentType) {
        validateCommentId(commentId);

        if (commentType == null) {
            throw new IllegalArgumentException("댓글 타입이 null입니다.");
        }
    }

    private void validateCommentId(long commentId) {
        if (commentId <= 0) {
            throw new IllegalArgumentException("유효하지 않은 댓글 ID: " + commentId);
        }
    }

    private void validateMember(Member member) {
        if (member == null) {
            throw new CommentException(CommentErrorCode.USER_NOT_AUTHENTICATED_ERROR);
        }
    }

    private void validateCommentOwnership(Comment comment, Member member) {
        if (comment.getMember().getId() != member.getId()) {
            log.warn("Comment ownership validation failed. Comment owner: {}, Request member: {}",
                    comment.getMember().getId(), member.getId());
            throw new CommentException(CommentErrorCode.COMMENT_AUTHOR_ONLY_ERROR,
                    "현재 사용자: " + member.getId() + ", 작성자: " + comment.getMember().getId());
        }
    }
}
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
    @Transactional(rollbackFor = Exception.class)
    public Comment createComment(CreateCommentRequest request, Member member) {
        log.debug("Creating comment for member: {}, target: {}, type: {}",
                member.getId(), request.getTarget(), request.getType());

        validateCreateCommentRequest(request, member);

        Comment newComment = request.toEntity(member);
        commentRepository.save(newComment);

        log.info("Comment created successfully for member: {}", member.getId());
        return newComment;
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

        return commentRepository.findByTargetAndTypeOrderByCreatedAtDesc(request.getTarget(), request.getType());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateComment(UpdateCommentRequest request, Member member) {
        validateUpdateCommentRequest(request, member);

        Comment comment = findComment(request.getCommentId());
        validateCommentOwnership(comment, member);

        String previousContent = comment.getContent();
        comment.updateContent(request.getContent());

        log.info("Comment updated. ID: {}, Type: {}, Member: {}",
                comment.getId(), comment.getType(), member.getId());
        log.debug("Content changed from: '{}' to: '{}'", previousContent, request.getContent());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteComment(Long commentId, CommentType commentType, Member member) {
        validateCommentIdAndType(commentId, commentType);
        validateMember(member);

        Comment comment = findComment(commentId, commentType);
        validateCommentOwnership(comment, member);

        commentRepository.delete(comment);

        log.info("Comment deleted. ID: {}, Type: {}, Member: {}",
                commentId, commentType, member.getId());
    }

    private Comment findComment(long commentId) {
        validateCommentId(commentId);

        return commentRepository.findById(commentId)
                .orElseThrow(() -> {
                    log.error("Comment not found with CommentId: {}", commentId);
                    return new CommentException(CommentErrorCode.COMMENT_NOT_FOUND_ERROR);
                });
    }

    private Comment findComment(long commentId, CommentType commentType) {
        return commentRepository.findByIdAndType(commentId, commentType)
                .orElseThrow(() -> {
                    log.error("Comment not found with ID: {} and Type: {}", commentId, commentType);
                    return new CommentException(CommentErrorCode.COMMENT_NOT_FOUND_ERROR);
                });
    }

    private Comment findCommentByCommentType(long commentId, CommentType commentType) {
        return commentRepository.findByIdAndType(commentId, commentType)
                .orElseThrow(() -> {
                    log.error("Comment not found with ID: {} and Type: {}", commentId, commentType);
                    return new CommentException(CommentErrorCode.COMMENT_NOT_FOUND_ERROR);
                });
    }

    private void validateCreateCommentRequest(CreateCommentRequest request, Member member) {
        if (request == null) {
            log.error("CreateCommentRequest is null");
            throw new CommentException(CommentErrorCode.COMMENT_CONTENT_EMPTY_ERROR);
        }

        validateMember(member);

        if (!StringUtils.hasText(request.getContent())) {
            log.error("Comment content is empty");
            throw new CommentException(CommentErrorCode.COMMENT_CONTENT_EMPTY_ERROR);
        }

        if (request.getContent().length() > MAX_COMMENT_LENGTH) {
            log.error("Comment content too long. Length: {}, Max: {}",
                    request.getContent().length(), MAX_COMMENT_LENGTH);
            throw new CommentException(CommentErrorCode.COMMENT_CONTENT_TOO_LONG_ERROR);
        }

        if (request.getType() == null) {
            log.error("Comment type is null");
            throw new CommentException(CommentErrorCode.COMMENT_CONTENT_EMPTY_ERROR);
        }
    }

    private void validateUpdateCommentRequest(UpdateCommentRequest request, Member member) {
        if (request == null) {
            log.error("UpdateCommentRequest is null");
            throw new CommentException(CommentErrorCode.COMMENT_CONTENT_EMPTY_ERROR);
        }

        validateMember(member);
        validateCommentId(request.getCommentId());

        if (!StringUtils.hasText(request.getContent())) {
            log.error("Comment content is empty for update");
            throw new CommentException(CommentErrorCode.COMMENT_CONTENT_EMPTY_ERROR);
        }

        if (request.getContent().length() > MAX_COMMENT_LENGTH) {
            log.error("Comment content too long for update. Length: {}, Max: {}",
                    request.getContent().length(), MAX_COMMENT_LENGTH);
            throw new CommentException(CommentErrorCode.COMMENT_CONTENT_TOO_LONG_ERROR);
        }
    }

    private void validateGetCommentRequest(GetCommentRequest request) {
        if (request == null) {
            log.error("GetCommentRequest is null");
            throw new CommentException(CommentErrorCode.COMMENT_CONTENT_EMPTY_ERROR);
        }

        if (request.getType() == null) {
            log.error("Comment type is null for get request");
            throw new CommentException(CommentErrorCode.COMMENT_CONTENT_EMPTY_ERROR);
        }
    }

    private void validateCommentIdAndType(long commentId, CommentType commentType) {
        validateCommentId(commentId);

        if (commentType == null) {
            log.error("Comment type is null");
            throw new CommentException(CommentErrorCode.COMMENT_CONTENT_EMPTY_ERROR);
        }
    }

    private void validateCommentId(long commentId) {
        if (commentId <= 0) {
            log.error("Invalid comment ID: {}", commentId);
            throw new CommentException(CommentErrorCode.COMMENT_NOT_FOUND_ERROR);
        }
    }

    private void validateMember(Member member) {
        if (member == null) {
            log.error("Member is null - user not authenticated");
            throw new CommentException(CommentErrorCode.USER_NOT_AUTHENTICATED_ERROR);
        }
    }

    private void validateCommentOwnership(Comment comment, Member member) {
        if (comment.getMember().getId() != member.getId()) {
            log.error("Comment ownership validation failed. Comment owner: {}, Request member: {}",
                    comment.getMember().getId(), member.getId());
            throw new CommentException(CommentErrorCode.COMMENT_AUTHOR_ONLY_ERROR);
        }
    }
}
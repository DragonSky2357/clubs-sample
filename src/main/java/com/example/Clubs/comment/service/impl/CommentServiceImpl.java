package com.example.Clubs.comment.service.Impl;

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
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public void createComment(CreateCommentRequest request, Member member) {
        Comment newComment = request.toEntity(member);
        commentRepository.save(newComment);
    }

    @Override
    public Comment getComment(long commentId) {
        return findComment(commentId);
    }

    @Override
    public Comment getCommentByCommentType(long commentId, CommentType commentType) {
        return findCommentByCommentType(commentId, commentType);
    }

    @Override
    public List<Comment> getComments(GetCommentRequest request) {
        return commentRepository.findByTargetAndType(request.getTarget(), request.getType());
    }

    @Transactional
    @Override
    public void updateComment(UpdateCommentRequest request, Member member) {
        Comment comment = findComment(request.getCommentId());

        if (comment.getMember().getId() != member.getId()) {
            throw new CommentException(CommentErrorCode.COMMENT_NOTFOUND_ERROR);
        }

        comment.updateContent(request.getContent());
    }

    @Override
    @Transactional
    public void deleteComment(long commentId, Member member) {
        Comment comment = findComment(commentId);

        if (comment.getMember().getId() != member.getId())
        {
            throw new CommentException(CommentErrorCode.COMMENT_NOTFOUND_ERROR);
        }

        commentRepository.delete(comment); // Soft delete시, isdelete로 변경해야함. 여기선 hard delete
    }

    private Comment findComment(long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_NOTFOUND_ERROR));
    }

    private Comment findCommentByCommentType(long commentId, CommentType commentType) {
        return commentRepository.findByIdAndType(commentId, commentType)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_NOTFOUND_ERROR));
    }
}
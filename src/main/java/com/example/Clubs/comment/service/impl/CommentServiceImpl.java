package com.example.Clubs.comment.service.impl;

import com.example.Clubs.comment.dto.request.GetCommentRequest;
import com.example.Clubs.comment.dto.request.UpdateCommentRequest;
import com.example.Clubs.comment.dto.request.CreateCommentRequest;
import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import com.example.Clubs.comment.exception.CommentErrorCode;
import com.example.Clubs.comment.exception.CommentException;
import com.example.Clubs.comment.repository.CommentRepository;
import com.example.Clubs.comment.service.CommentService;
import com.example.Clubs.config.security.entity.User;
import com.example.Clubs.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public void createComment(CreateCommentRequest request, @AuthenticationPrincipal User member) {
        Comment newComment = request.toEntity(member);

        commentRepository.save(newComment);
    }

    @Override
    public Comment getComment(long commentId) {
        return findComment(commentId);
    }

    @Override
    public Comment getCommentByCommentType(long commnetId, CommentType commentType) {
        return findCommentByCommentType(commnetId, commentType);
    }

    @Override
    public List<Comment> getComments(GetCommentRequest request) {
        return commentRepository.findByTargetAndType(request.getTarget(), request.getType());
    }

    @Override
    public void updateComment(UpdateCommentRequest request, Member member) {

    }

    @Override
    public void deleteComment(long commentId, Member member) {

    }

    private Comment findComment(long commetId){
        return commentRepository.findById(commetId)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_NOTFOUND_ERROR));
    }

    private Comment findCommentByCommentType(long commetId, CommentType commentType){
        return commentRepository.findByIdAndType(commetId,commentType)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_NOTFOUND_ERROR));
    }
}

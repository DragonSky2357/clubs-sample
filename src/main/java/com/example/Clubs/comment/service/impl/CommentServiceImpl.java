package com.example.Clubs.comment.service.impl;

import com.example.Clubs.comment.dto.GetCommentRequest;
import com.example.Clubs.comment.dto.UpdateCommentRequest;
import com.example.Clubs.comment.dto.request.CreateCommentRequest;
import com.example.Clubs.comment.entity.Comment;
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
    public void createComment(CreateCommentRequest request) {
        // var a = member.getMember();
        //Comment comment = request.toEntity();
    }

    @Override
    public Comment getComment(long commentId) {
        return null;
    }

    @Override
    public List<Comment> getComments(GetCommentRequest request) {
        return List.of();
    }

    @Override
    public void updateComment(UpdateCommentRequest request, Member member) {

    }

    @Override
    public void deleteComment(long commentId, Member member) {

    }
}

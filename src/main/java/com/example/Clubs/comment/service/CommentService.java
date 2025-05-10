package com.example.Clubs.comment.service;

import com.example.Clubs.comment.dto.request.GetCommentRequest;
import com.example.Clubs.comment.dto.request.UpdateCommentRequest;
import com.example.Clubs.comment.dto.request.CreateCommentRequest;
import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import com.example.Clubs.config.security.entity.User;
import com.example.Clubs.member.entity.Member;

import java.util.List;

public interface CommentService {
    void createComment(CreateCommentRequest request, User member);

    Comment getComment(long commentId);

    Comment getCommentByCommentType(long commnetId, CommentType commentType);

    List<Comment> getComments(GetCommentRequest request);

    void updateComment(UpdateCommentRequest request, Member member);

    void deleteComment(long commentId, Member member);
}

package com.example.Clubs.comment.service;

import com.example.Clubs.comment.dto.request.CreateCommentRequest;
import com.example.Clubs.comment.dto.request.GetCommentRequest;
import com.example.Clubs.comment.dto.request.UpdateCommentRequest;
import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import com.example.Clubs.member.entity.Member;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CommentService {
    void createComment(CreateCommentRequest request, Member member);
    Comment getComment(long commentId);
    List<Comment> getComments(GetCommentRequest request);
    void updateComment(UpdateCommentRequest request, Member member);
    Comment getCommentByCommentType(long commentId, CommentType commentType);
    void deleteComment(long commentId, Member member);
}
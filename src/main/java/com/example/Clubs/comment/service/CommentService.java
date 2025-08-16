package com.example.Clubs.comment.service;

import com.example.Clubs.comment.dto.request.CreateCommentRequest;
import com.example.Clubs.comment.dto.request.GetCommentRequest;
import com.example.Clubs.comment.dto.request.UpdateCommentRequest;
import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentId;
import com.example.Clubs.comment.entity.CommentType;
import com.example.Clubs.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {
    // 새 댓글 생성
    void createComment(CreateCommentRequest request, Member member);

    // 댓글 조회
    Comment getComment(long commentId);

    // ID와 타입으로 댓글 조회
    Comment getCommentByIdAndType(long commentId, CommentType commentType);

    // 조건에 따른 댓글 목록 조회
    List<Comment> getComments(GetCommentRequest request);

    // 댓글 내용 수정
    void updateComment(UpdateCommentRequest request, Member member);

    // 댓글 삭제(소프트 삭제)
    void deleteComment(Long commentId, CommentType commentType, Member member);
}
package com.example.Clubs.comment.controller;

import com.example.Clubs.comment.converter.StringToCommentTypeConverter;
import com.example.Clubs.comment.dto.request.CreateCommentRequest;
import com.example.Clubs.comment.dto.request.GetCommentRequest;
import com.example.Clubs.comment.dto.request.UpdateCommentRequest;
import com.example.Clubs.comment.dto.response.CommentResponse;
import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import com.example.Clubs.comment.service.CommentService;
import com.example.Clubs.member.entity.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/comment")
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private final StringToCommentTypeConverter typeConverter;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @RequestBody @Valid CreateCommentRequest request,
            @AuthenticationPrincipal Member member) {

        log.info("Creating comment for member: {}, target: {}, type: {}",
                member.getId(), request.getTarget(), request.getType());

        Comment createdComment = commentService.createComment(request, member);
        CommentResponse response = CommentResponse.from(createdComment);
        return ResponseEntity.ok().build();
    }

    // 특정 대상의 댓글 목록 조회
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(
            @RequestParam long target,
            @RequestParam CommentType type) {

        GetCommentRequest req = new GetCommentRequest();
        req.setTarget(target);
        req.setType(type);

        List<CommentResponse> list = commentService.getComments(req)
                .stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    /*
    // 특정 대상의 댓글 목록을 페이징하여 조회
    @GetMapping("/paged")
    public ResponseEntity<Page<CommentResponse>> getCommentsPaged(
            @RequestParam Long target,
            @RequestParam CommentType type,
            @PageableDefault(size = 20) Pageable pageable) {

        log.debug("Fetching paged comments for target: {}, type: {}, page: {}",
                target, type, pageable.getPageNumber());

        GetCommentRequest request = new GetCommentRequest();
        request.setTarget(target);
        request.setType(type);

        Page<CommentResponse> responses = commentService.getCommentsPaged(request, pageable)
                .map(CommentResponse::from);

        return ResponseEntity.ok(responses);
    }

     */

    // 특정 댓글 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> getComment(
            @PathVariable Long commentId,
            @RequestParam CommentType type) {

        log.debug("Fetching comment ID: {}, Type: {}", commentId, type);

        Comment comment = commentService.getCommentByIdAndType(commentId, type);
        CommentResponse response = CommentResponse.from(comment);

        return ResponseEntity.ok(response);
    }

    // 댓글 내용 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(
            @PathVariable Long commentId,
            @RequestParam CommentType type,
            @RequestBody @Valid UpdateCommentRequest request,
            @AuthenticationPrincipal Member member) {

        log.info("Updating comment ID: {}, Type: {}, Member: {}",
                commentId, type, member.getId());

        // URL 파라미터로 받은 값을 request에 설정
        request.setCommentId(commentId);
        request.setCommentType(type);

        commentService.updateComment(request, member);
        return ResponseEntity.ok().build();
    }

    // 댓글 삭제(소프트 삭제)
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable long commentId,
            @RequestParam CommentType type,
            @AuthenticationPrincipal Member member) {

        log.info("Deleting comment ID: {}, Type: {}, Member: {}",
                commentId, type, member.getId());

        commentService.deleteComment(commentId, type, member);
        return ResponseEntity.ok().build();
    }
}
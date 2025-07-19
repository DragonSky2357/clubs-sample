package com.example.Clubs.comment.controller;

import com.example.Clubs.comment.converter.StringToCommentTypeConverter;
import com.example.Clubs.comment.dto.request.CreateCommentRequest;
import com.example.Clubs.comment.dto.request.GetCommentRequest;
import com.example.Clubs.comment.dto.request.UpdateCommentRequest;
import com.example.Clubs.comment.dto.CommentResponse;
import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import com.example.Clubs.comment.service.CommentService;
import com.example.Clubs.member.entity.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
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

    @PostMapping
    public ResponseEntity<Void> createComment(
            @RequestBody @Valid CreateCommentRequest request,
            @AuthenticationPrincipal Member member) {

        commentService.createComment(request, member);
        return ResponseEntity.ok().build();
    }

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

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable long commentId) {
        Comment comment = commentService.getComment(commentId);
        return ResponseEntity.ok(CommentResponse.from(comment));
    }

    @PatchMapping
    public ResponseEntity<Void> updateComment(
            @RequestBody @Valid UpdateCommentRequest request,
            @AuthenticationPrincipal Member member) {

        commentService.updateComment(request, member);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable long commentId,
            @AuthenticationPrincipal Member member) {

        commentService.deleteComment(commentId, member);
        return ResponseEntity.ok().build();
    }
}
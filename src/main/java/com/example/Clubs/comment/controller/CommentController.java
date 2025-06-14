package com.example.Clubs.comment.controller;

import com.example.Clubs.comment.dto.request.CreateCommentRequest;
import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import com.example.Clubs.comment.service.CommentService;
import com.example.Clubs.config.security.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping()
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/v1/comment")
    public ResponseEntity createComment(@RequestBody @Valid CreateCommentRequest request, @AuthenticationPrincipal User member) {
        commentService.createComment(request,member);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{commentType}/{targetId}/comments/{commentId}")
    public ResponseEntity<Comment> getComment(
            @PathVariable("commentType") CommentType commentType,
            @PathVariable("targetId") long targetId,
            @PathVariable("commentId") long commentId) {

        Comment comment = commentService.getComment(commentType, targetId, commentId);
        return ResponseEntity.ok(comment);
    }
}

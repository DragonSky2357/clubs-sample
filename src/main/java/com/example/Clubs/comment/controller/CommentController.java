package com.example.Clubs.comment.controller;

import com.example.Clubs.comment.dto.request.CreateCommentRequest;
import com.example.Clubs.comment.service.CommentService;
import com.example.Clubs.config.security.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/comment")
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity createComment(@RequestBody @Valid CreateCommentRequest request, @AuthenticationPrincipal User member) {
        commentService.createComment(request,member);
        return ResponseEntity.ok().build();
    }
}

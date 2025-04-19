package com.example.Clubs.post.controller;

import com.example.Clubs.config.security.entity.User;
import com.example.Clubs.post.dto.request.CreatePostRequest;
import com.example.Clubs.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/post")
@Slf4j
public class PostController {
    private final PostService postService;

    @PostMapping()
    public ResponseEntity createPost(@RequestBody CreatePostRequest request, @AuthenticationPrincipal User user){
        postService.createPost(request,user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity getPost(@PathVariable long postId){
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deltePost(@PathVariable long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}

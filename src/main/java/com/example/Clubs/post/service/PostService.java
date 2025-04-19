package com.example.Clubs.post.service;

import com.example.Clubs.config.security.entity.User;
import com.example.Clubs.post.dto.request.CreatePostRequest;
import com.example.Clubs.post.dto.request.UpdatePostReqeust;
import com.example.Clubs.post.dto.response.GetPostResponse;

public interface PostService {
    void createPost(CreatePostRequest request, User user);
    GetPostResponse getPost(long postId);
    void updatePost(long postId, UpdatePostReqeust reqeust);
    void deletePost(long postId);
}

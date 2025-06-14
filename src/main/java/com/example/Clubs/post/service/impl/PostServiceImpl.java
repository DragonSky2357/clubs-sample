package com.example.Clubs.post.service.impl;

import com.example.Clubs.config.security.entity.User;
import com.example.Clubs.member.entity.Member;
import com.example.Clubs.member.repository.MemberRepository;
import com.example.Clubs.post.dto.request.CreatePostRequest;
import com.example.Clubs.post.dto.request.UpdatePostReqeust;
import com.example.Clubs.post.dto.response.GetPostResponse;
import com.example.Clubs.post.entity.Post;
import com.example.Clubs.post.exception.PostErrorCode;
import com.example.Clubs.post.exception.PostException;
import com.example.Clubs.post.mapper.PostMapper;
import com.example.Clubs.post.repository.PostRepository;
import com.example.Clubs.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PostMapper postMapper;

    @Override
    @Transactional
    public void createPost(CreatePostRequest request, User user) {
        Member findMember = memberRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

        Post newPost = request.toEntity(findMember);
        postRepository.save(newPost);
    }

    @Override
    public GetPostResponse getPost(long postId) {
//        Post findPost = findPost(postId);
        return postMapper.getPost(postId);
    }

    @Override
    @Transactional
    public void updatePost(long postId, UpdatePostReqeust reqeust) {
        Post findPost = findPost(postId);
        Post.updatePost(findPost,reqeust);
    }

    @Override
    @Transactional
    public void deletePost(long postId) {
        Post findPost = findPost(postId);
        postRepository.delete(findPost);
    }

    private Post findPost(long postId){
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostException(PostErrorCode.POST_NOTFOUND_ERROR));
    }
}

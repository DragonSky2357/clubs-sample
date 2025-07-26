package com.example.Clubs.post.mapper;

import com.example.Clubs.config.security.entity.User;
import com.example.Clubs.post.dto.response.GetPostResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
    User selectUserById(Long id);

    GetPostResponse getPost(long postId);
}

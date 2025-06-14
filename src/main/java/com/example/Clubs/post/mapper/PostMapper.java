package com.example.Clubs.post.mapper;

import com.example.Clubs.post.dto.response.GetPostResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostMapper {
    GetPostResponse getPost(@Param("postId") long postId);
}

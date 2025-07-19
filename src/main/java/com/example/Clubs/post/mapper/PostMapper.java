package com.example.Clubs.post.mapper;

import com.example.Clubs.config.security.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
    User selectUserById(Long id);
}

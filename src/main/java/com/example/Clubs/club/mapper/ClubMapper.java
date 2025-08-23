package com.example.Clubs.club.mapper;

import com.example.Clubs.club.dto.response.ClubResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClubMapper {

    ClubResponse getClubByIdWithActiveStatus(@Param("clubId") long clubId, @Param("isActive") Boolean flag);

    List<ClubResponse> getClubByOwnerIdWithActiveStatus(@Param("ownerId") long ownerId, @Param("isActive") Boolean flag);


}

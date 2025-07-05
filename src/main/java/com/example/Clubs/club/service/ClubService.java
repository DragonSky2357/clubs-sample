package com.example.Clubs.club.service;

import com.example.Clubs.club.dto.request.CreateClubRequest;
import com.example.Clubs.club.dto.request.UpdateClubRequest;
import com.example.Clubs.club.dto.response.ClubResponse;

import java.util.List;

public interface ClubService {

    ClubResponse GetClubById(Long clubId);

    List<ClubResponse> GetClubByOwnerId(Long ownerId);

    void createClub(CreateClubRequest request, Long memberId);

    void updateClub(UpdateClubRequest request, Long clubId, Long ownerId);

    void deleteClub(Long id, Long ownerId);
}

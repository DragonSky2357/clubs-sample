package com.example.Clubs.club.controller;

import com.example.Clubs.club.dto.request.CreateClubRequest;
import com.example.Clubs.club.dto.request.UpdateClubRequest;
import com.example.Clubs.club.dto.response.ClubResponse;
import com.example.Clubs.club.service.impl.ClubServiceImpl;
import com.example.Clubs.config.security.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ClubController {

    private final ClubServiceImpl clubService;

    @GetMapping("/v1/club/{clubId}")
    public ResponseEntity<ClubResponse> GetClubById(@PathVariable Long clubId){
        ClubResponse response = clubService.GetClubById(clubId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/v1/club/owner")
    public ResponseEntity<List<ClubResponse>> GetClubByOwnerId(@AuthenticationPrincipal User user){
        List<ClubResponse> response = clubService.GetClubByOwnerId(user.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/v1/club")
    public ResponseEntity<Void> createClub(@RequestBody @Valid CreateClubRequest request, @AuthenticationPrincipal User user){
        clubService.createClub(request, user.getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/v1/club/{clubId}")
    public ResponseEntity<Void> updateClub(@RequestBody @Valid UpdateClubRequest request, @PathVariable Long clubId, @AuthenticationPrincipal User owner){
        clubService.updateClub(request,clubId,owner.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/v1/club/{clubId}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long clubId,@AuthenticationPrincipal User owner){
        clubService.deleteClub(clubId, owner.getId());
        return ResponseEntity.ok().build();
    }
}
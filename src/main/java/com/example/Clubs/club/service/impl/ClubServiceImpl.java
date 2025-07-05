package com.example.Clubs.club.service.impl;

import com.example.Clubs.club.dto.request.CreateClubRequest;
import com.example.Clubs.club.dto.request.UpdateClubRequest;
import com.example.Clubs.club.dto.response.ClubResponse;
import com.example.Clubs.club.entity.Club;
import com.example.Clubs.club.exception.ClubErrorCode;
import com.example.Clubs.club.exception.ClubException;
import com.example.Clubs.club.repository.ClubRepository;
import com.example.Clubs.club.service.ClubService;
import com.example.Clubs.member.entity.Member;
import com.example.Clubs.member.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final MemberServiceImpl memberService;

    @Override
    public ClubResponse GetClubById(Long clubId) {
        Club entity = findClubById(clubId);
        return entityToResponseDto(entity);
    }

    @Override
    public List<ClubResponse> GetClubByOwnerId(Long ownerId) {
        List<ClubResponse> returnValue = new ArrayList<>();
        List<Club> clubs = clubRepository.findByOwnerId(ownerId);

        if (clubs == null || clubs.isEmpty()) {
            throw new ClubException(ClubErrorCode.CLUB_NOTFOUND_ERROR);
        }

        for (Club c : clubs) {
            returnValue.add(entityToResponseDto(c));
        }


        return returnValue;
    }

    @Override
    public void createClub(CreateClubRequest requestDto, Long memberId) {
        Member member = memberService.getMemberById(memberId);

        Club saveClub = Club.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .owner(member)
                .isActive(true)
                .build();

        clubRepository.save(saveClub);
    }

    @Override
    public void updateClub(UpdateClubRequest requestDto, Long clubId, Long ownerId) {
        Club savedClub = findClubById(clubId);
        if (savedClub.getOwner().getId() != ownerId) {             throw new ClubException(ClubErrorCode.CLUB_PERMISSION_ERROR);}
        savedClub.update(requestDto);
        clubRepository.save(savedClub);
    }

    @Override
    public void deleteClub(Long clubId, Long ownerId) {
        if (findClubById(clubId).getId() != ownerId){
            throw new ClubException(ClubErrorCode.CLUB_PERMISSION_ERROR);
        }
        clubRepository.deleteById(clubId);
    }

    private Club findClubById(Long clubId) {
        return clubRepository.findById(clubId)
                .orElseThrow(() -> new ClubException(ClubErrorCode.CLUB_NOTFOUND_ERROR));
    }

    private ClubResponse entityToResponseDto(Club entity) {
        return ClubResponse.builder()
                .clubId(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .ownerUserName(entity.getOwner().getUsername())
                .build();
    }
}

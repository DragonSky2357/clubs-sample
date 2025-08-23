package com.example.Clubs.club.service.impl;

import com.example.Clubs.club.dto.request.CreateClubRequest;
import com.example.Clubs.club.dto.request.UpdateClubRequest;
import com.example.Clubs.club.dto.response.ClubResponse;
import com.example.Clubs.club.entity.Club;
import com.example.Clubs.club.exception.ClubErrorCode;
import com.example.Clubs.club.exception.ClubException;
import com.example.Clubs.club.mapper.ClubMapper;
import com.example.Clubs.club.repository.ClubRepository;
import com.example.Clubs.club.service.ClubService;
import com.example.Clubs.member.entity.Member;
import com.example.Clubs.member.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.Clubs.club.dto.ClubDtoConvertor.CreateClubRequestToEntity;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper;
    private final MemberServiceImpl memberService;

    /*
     *  Club엔티티의 id값으로 조회하여 반환 하는 메소드
     *  Club을 찾을 수 없는 경우 ClubException(NOT_FOUND) 예외를 던집니다.
     *
     *  @Param : 조회할 Club의 id
     *  @return : Club의 Response Dto
     *
     */
    @Override
    public ClubResponse GetClubById(Long clubId) {
//        Club entity = findClubById(clubId);
//        log.info("[Club] club 조회됨 id -> {}", clubId);
//        return entityToResponseDto(entity);
        ClubResponse response = clubMapper.getClubByIdWithActiveStatus(clubId, true);
        if (response == null) {
            throw new ClubException(ClubErrorCode.CLUB_NOTFOUND_ERROR);
        }
        return response;
    }

    /*
     * 파라메터로 받은 유저 아이디를 통해 그 유저가
     * 소유하고 있는 club들을 찾아 dto로 반환 합니다.
     * 조회 실패 및 없을시 ClubException(NOT_FOUND)예외를 던집니다.
     *
     * @Param : 조회할 유저의 id
     * @Return : 조회한 club들의 response dto 리스트
     */
    @Override
    public List<ClubResponse> GetClubByOwnerId(Long requestUserId) {
//        List<Club> clubs = clubRepository.findByOwnerId(requestUserId);
//        if (clubs == null || clubs.isEmpty()) {
//            log.warn("[Club] id -> {} 유저 소유 Club 없음", requestUserId);
//            throw new ClubException(ClubErrorCode.CLUB_NOTFOUND_ERROR);
//        }
//        log.info("[Club] id -> {} 유저 소유 Club 조회 됨", requestUserId);
//        return clubs.stream()
//                .map(ClubDtoConvertor::entityToResponseDto)
//                .toList();

        List<ClubResponse> clubList = clubMapper.getClubByOwnerIdWithActiveStatus(requestUserId, true);
        if (clubList == null || clubList.isEmpty()) {
            log.warn("[Club] id -> {} 유저 소유 Club 없음", requestUserId);
            throw new ClubException(ClubErrorCode.CLUB_NOTFOUND_ERROR);
        }
        log.info("[Club] id -> {} 유저 소유 Club 조회 됨", requestUserId);
        return clubList;

    }


    /*
     *
     * createRequestDto를 이용하여 club 엔티티 생성 및 저장을 합니다
     *
     * @Param : 클럽생성요청DTO, 클럽 생성 요청을 한 유저의 id
     *
     */
    @Override
    @Transactional
    public void createClub(CreateClubRequest requestDto, Long requestUserId) {
        Member member = memberService.getMemberById(requestUserId);
        log.info("[Club] searchId -> {}  findId -> {} 맴버 조회 완료", requestUserId, member.getId());
        Club saveClub = CreateClubRequestToEntity(requestDto, member);
        saveOrThrow(saveClub);
    }

    /*
     * 수정요청dto를 받아 수정 후 저장합니다.
     * 클럽을 검색한 후 검색한 클럽의 id와 요청 유저의 id를 비교하여
     * 다를 경우 PERMISSION_ERROR를 던집니다.
     *
     * @Param : 수정요청DTO, 요청한 유저의 id
     */
    @Override
    @Transactional
    public void updateClub(UpdateClubRequest requestDto, Long clubId, Long requestUserId) {
        Club savedClub = findClubById(clubId);
        Member clubOwner = savedClub.getOwner();
        log.info("[Club] club 조회됨 clubId -> {}", savedClub.getId());
        if (clubOwner.getId() != requestUserId) {
            log.warn("[Club] club의 owner와 요청자의 id 가 다름 owner -> {}   requestUser -> {}", clubOwner.getId(), requestUserId);
            throw new ClubException(ClubErrorCode.CLUB_PERMISSION_ERROR);
        }
        savedClub.update(requestDto);
        clubRepository.save(savedClub);
        log.info("[Club] 수정 완료 수정된 Club Id -> {}", savedClub.getId());
    }

    /*
     * 클럽을 softDelete하는 메소드입니다.
     * 클럽을 조회한 후 owner의 id와 요청한 유저의 id를 비교하여
     * 다를 경우 PERMISSION_ERROR를 던집니다.
     *
     * @Param : 삭제할 클럽 id, 요청한 유저의 id
     *
     */
    @Override
    @Transactional
    public void deleteClub(Long clubId, Long requestUserId) {
        Club club = findClubById(clubId);
        Member clubOwner = club.getOwner();
        if (club.getId() != requestUserId) {
            log.warn("[Club] 유저와 클럽 owner가 서로 다름 requestUserId -> {}   clubOwnerId -> {}", requestUserId, clubOwner.getId());
            throw new ClubException(ClubErrorCode.CLUB_PERMISSION_ERROR);
        }
        clubRepository.delete(club);
        clubRepository.save(club);
        log.info("[Club] softDelete완료 id -> {}", club.getId());
    }

    private Club findClubById(Long clubId) {
        return clubRepository.findById(clubId)
                .orElseThrow(() -> new ClubException(ClubErrorCode.CLUB_NOTFOUND_ERROR));
    }

    private Club saveOrThrow(Club entity) {
        try {
            Club club = clubRepository.save(entity);
            log.info("[Club] 저장 완료");
            return club;
        } catch (Exception e) {
            log.warn("[Club] 저장 실패 \n {}", e.getMessage());
            throw new ClubException(ClubErrorCode.CLUB_CREATE_ERROR);
        }
    }

}

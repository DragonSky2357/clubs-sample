package com.example.Clubs.club.service;

import com.example.Clubs.club.dto.request.CreateClubRequest;
import com.example.Clubs.club.dto.request.UpdateClubRequest;
import com.example.Clubs.club.dto.response.ClubResponse;

import java.util.List;

public interface ClubService {

    /*
    * 클럽을 조회 한 후 DTO로 변환 후 리턴
    *
    * @Parma : 클럽id
    * @Return : 검색한 클럽의 response Dto
     */
    ClubResponse GetClubById(Long clubId);


    /*
     * 유저가 소유한 club을 조회한 후 DTO변환 및 리턴
     *
     * @Parma : 유저id
     * @Return : 검색한 클럽의 response Dto 리스트
     */
    List<ClubResponse> GetClubByOwnerId(Long userId);

    /*
     *
     * 생성요청DTO, 생성요청 유저 id를 파람으로 받은 후
     * 엔티티 생성 및 저장을 합니다.
     *
     */
    void createClub(CreateClubRequest request, Long userId);
    /*
     *
     * 수정요청DTO, 수정요청 유저 id를 파람으로 받은 후
     * 엔티티 수정 및 저장을 합니다.
     *
     */
    void updateClub(UpdateClubRequest request, Long clubId, Long userId);
    /*
     *
     * 클럽id, 삭제요청 유저 id를 파람으로 받은 후
     * 엔티티 삭제을 합니다.
     *
     */
    void deleteClub(Long id, Long userId);
}

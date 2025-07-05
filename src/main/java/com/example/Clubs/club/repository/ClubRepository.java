package com.example.Clubs.club.repository;

import com.example.Clubs.club.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {

    List<Club> findByOwnerId(long ownerId);
}

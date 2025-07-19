package com.example.Clubs.comment.repository;

import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTargetAndType(long target, CommentType type);
    Optional<Comment> findByIdAndType(long id, CommentType type); // java. lang. ScopedValue은(는) 테스트 버전 API이며 향후 릴리즈 버전에서 제거될 수 있기에 수정
}
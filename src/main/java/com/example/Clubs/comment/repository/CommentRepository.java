package com.example.Clubs.comment.repository;

import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT COALESCE(MAX(id), 0) + 1 FROM comment WHERE type = :type FOR UPDATE", nativeQuery = true)
    long generateId(@Param("type") String type);

    Optional<Comment> findByTypeAndTargetAndId(CommentType type, long target, long id);
    Optional<Comment> findByIdAndType(long commetId, CommentType commentType);
    List<Comment> findByTargetAndType(long targetId, CommentType commentType);
}

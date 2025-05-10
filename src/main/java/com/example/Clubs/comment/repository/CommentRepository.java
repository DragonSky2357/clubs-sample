package com.example.Clubs.comment.repository;

import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndType(long commetId, CommentType commentType);
    List<Comment> findByTargetAndType(long targetId, CommentType commentType);
}

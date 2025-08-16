package com.example.Clubs.comment.repository;

import com.example.Clubs.comment.entity.Comment;
import com.example.Clubs.comment.entity.CommentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글/클럽의 댓글 목록 조회(최신순)
    List<Comment> findByTargetAndTypeOrderByCreatedAtDesc(Long target, CommentType type);

    // ID와 Type으로 특정 댓글 조회
    Optional<Comment> findByIdAndType(Long id, CommentType type);

    // 특정 게시글/클럽의 댓글 목록을 페이징하여 조회(최신순)
    Page<Comment> findByTargetAndTypeOrderByCreatedAtDesc(Long target, CommentType type, Pageable pageable);

    // 특정 댓글의 대댓글 목록 조회(작성순)
    List<Comment> findByParentOrderByCreatedAtAsc(Long parentId);

    // 특정 게시물/클럽의 댓글 수 조회
    long countByTargetAndType(Long target, CommentType type);

    // 최상위 댓글만 조회(대댓글 제외)
    @Query("SELECT c FROM Comment c WHERE c.target = :target AND c.type = :type AND c.parent IS NULL ORDER BY c.createdAt DESC")
    List<Comment> findTopLevelCommentsByTargetAndType(@Param("target") Long target, @Param("type") CommentType type);


}
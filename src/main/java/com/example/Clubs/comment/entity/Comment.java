package com.example.Clubs.comment.entity;

import com.example.Clubs.common.entity.BaseEntity;
import com.example.Clubs.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.Builder.Default;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "comments")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE comment SET is_active = false WHERE id = ?")
@SQLRestriction("is_active = true")
@IdClass(CommentId.class)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommentType type;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @NotNull
    private Member member;

    @Column(nullable = false)
    private Long target;

    @Column(name = "parent_id")
    private Long parent;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    // 댓글 내용 수정 메소드
    public void updateContent(String content) {
        this.content = content;
    }

    // 대댓글인지 확인하는 메소드
    public boolean isReply() {
        return this.parent != null;
    }

    // 활성 상태 확인 메소드
    public boolean isActive() {
        return this.isActive != null && this.isActive;
    }
}
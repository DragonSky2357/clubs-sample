package com.example.Clubs.comment.entity;

import com.example.Clubs.common.entity.BaseEntity;
import com.example.Clubs.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.Lock;

@Entity(name = "comment")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE comment SET is_active = false WHERE id = ?")
@Where(clause = "is_active = true")
@IdClass(CommentId.class)
public class Comment extends BaseEntity {
    @Id
    private long id;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommentType type;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Member member;

    @Column(nullable = false)
    private long target;

    @Column
    private Long partent;

    @Column
    private boolean isActive;
}

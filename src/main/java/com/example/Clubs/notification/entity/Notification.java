package com.example.Clubs.notification.entity;

import com.example.Clubs.common.entity.BaseEntity;
import com.example.Clubs.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long notification_id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  @ToString.Exclude
  private Member member;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String message;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime created_at;

}

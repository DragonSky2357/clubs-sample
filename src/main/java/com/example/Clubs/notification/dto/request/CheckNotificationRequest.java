
package com.example.Clubs.notification.dto.request;

import com.example.Clubs.notification.entity.Notification;
import com.example.Clubs.member.entity.Member;
import lombok.Getter;

@Getter
public class CheckNotificationRequest {

  private Long member_id;
  private String message;

  public Notification toEntity(Member member) {
    return Notification.builder()
            .member(member)
            .message(this.message)
            .build();
  }
}
package com.example.Clubs.notification.dto.request;

import com.example.Clubs.member.entity.Member;
import com.example.Clubs.notification.entity.Notification;
import lombok.Getter;
import lombok.ToString;

@Getter
public class CreateNotificationRequest {

  private String message;

  public Notification toEntity(Member member) {
    return Notification.builder()
            .member(member)
            .message(this.message)
            .build();
  }

}

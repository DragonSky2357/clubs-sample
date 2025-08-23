package com.example.Clubs.notification.dto.response;

import com.example.Clubs.notification.entity.Notification;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReadNotificationResponse {
  private long id;
  private long memberId;
  private  String message;
  private LocalDateTime created_at;

  public static ReadNotificationResponse from(Notification notification) {
    return ReadNotificationResponse.builder()
            .id(notification.getId())
            .memberId(notification.getMember().getId())
            .message(notification.getMessage())
            .created_at(notification.getCreated_at())
            .build();
  }

}

package com.example.Clubs.notification.dto.response;

import com.example.Clubs.notification.entity.Notification;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CheckNotificationResponse {
  private long notification_id;
  private long member_id;
  private  String message;
  private LocalDateTime created_at;

  public static CheckNotificationResponse from(Notification notification) {
    return CheckNotificationResponse.builder()
            .notification_id(notification.getNotification_id())
            .member_id(notification.getMember().getId())
            .message(notification.getMessage())
            .created_at(notification.getCreated_at())
            .build();
  }

}

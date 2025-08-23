package com.example.Clubs.notification.dto.request;

import com.example.Clubs.member.entity.Member;
import com.example.Clubs.notification.entity.Notification;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
public class CreateNotificationRequest {

  @Max(value = 500, message = "최대 500글자 까지")
  @NotBlank(message = "메시지는 필수입니다")
  private String message;

  public Notification toEntity(Member member) {
    return Notification.builder()
            .member(member)
            .message(this.message)
            .isActive(true)
            .build();
  }

}

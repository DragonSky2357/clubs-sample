package com.example.Clubs.notification.service.impl;

import com.example.Clubs.member.entity.Member;
import com.example.Clubs.member.repository.MemberRepository;
import com.example.Clubs.notification.dto.request.CheckNotificationRequest;
import com.example.Clubs.notification.dto.response.CheckNotificationResponse;
import com.example.Clubs.notification.entity.Notification;
import com.example.Clubs.notification.repository.NotificationRepository;
import com.example.Clubs.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

  private final MemberRepository memberRepository;
  private final NotificationRepository notificationRepository;

  @Override
  public CheckNotificationResponse createNotification(CheckNotificationRequest request) {

    Member member = memberRepository.findById(request.getMember_id())
            .orElseThrow(() -> new RuntimeException("회원이 없습니다 : " + request.getMember_id()));

    return CheckNotificationResponse.from(
            notificationRepository.save(request.toEntity(member))
    );

  }

  @Override
  public CheckNotificationResponse selectNotification(long notification_id) {
    return CheckNotificationResponse.from(
            notificationRepository.findById(notification_id)
                    .orElseThrow(() -> new RuntimeException("알림을 찾을 수 없습니다: " + notification_id))
    );
  }

  @Override
  public List<CheckNotificationResponse> getAllNotifications() {
    return notificationRepository.findAll()
            .stream()
            .map(CheckNotificationResponse::from)
            .toList();
  }

  @Override
  public CheckNotificationResponse updateNotification(CheckNotificationRequest request, long notification_id) {
    notificationRepository.findById(notification_id)
            .orElseThrow(() -> new RuntimeException("알림을 찾을 수 없습니다: " + notification_id));

    Member member = memberRepository.findById(request.getMember_id())
            .orElseThrow(() -> new RuntimeException("회원이 없습니다: " + request.getMember_id()));

    Notification newNotification = Notification.builder()
            .notification_id(notification_id)
            .member(member)
            .message(request.getMessage())
            .build();

    return CheckNotificationResponse.from(notificationRepository.save(newNotification));
  }

  @Override
  public void deleteNotification(long notification_id) {
    Notification notification = notificationRepository.findById(notification_id)
            .orElseThrow(() -> new RuntimeException("알림을 찾을 수 없습니다: " + notification_id));
    notificationRepository.delete(notification);
  }
}

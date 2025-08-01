package com.example.Clubs.notification.service.impl;

import com.example.Clubs.member.entity.Member;
import com.example.Clubs.member.repository.MemberRepository;
import com.example.Clubs.notification.dto.request.CheckNotificationRequest;
import com.example.Clubs.notification.dto.request.CreateNotificationRequest;
import com.example.Clubs.notification.dto.request.UpdateNotificationRequest;
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
  public CheckNotificationResponse createNotification(CreateNotificationRequest request, long member_id) {

    Member member = memberRepository.findById(member_id)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다 : " + member_id));

    Notification notification =  request.toEntity(member);

    Notification result = notificationRepository.save(notification);

    return CheckNotificationResponse.from(result);

  }

  @Override
  public CheckNotificationResponse selectNotification(long notification_id) {

    Notification notification = notificationRepository.findById(notification_id).orElseThrow(() -> new RuntimeException("알림을 찾을 수 없습니다: " + notification_id));

    return CheckNotificationResponse.from(notification);
  }

  @Override
  public List<CheckNotificationResponse> getAllNotifications() {
    return notificationRepository.findAll()
            .stream()
            .map(CheckNotificationResponse::from)
            .toList();
  }

  @Override
  public CheckNotificationResponse updateNotification(UpdateNotificationRequest request, long member_id,long notification_id) {

    notificationRepository.findById(notification_id)
            .orElseThrow(() -> new RuntimeException("알림을 찾을 수 없습니다: " + notification_id));

    Member member = memberRepository.findById(member_id)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다: " + member_id));

    if(member_id != member.getId()) throw(new RuntimeException("권한이 없습니다: " + member_id));

    Notification newNotification = Notification.builder()
            .notification_id(notification_id)
            .member(member)
            .message(request.getMessage())
            .build();

    return CheckNotificationResponse.from(notificationRepository.save(newNotification));
  }

  @Override
  public void deleteNotification(long notification_id, long member_id) {

    Notification notification = notificationRepository.findById(notification_id)
            .orElseThrow(() -> new RuntimeException("알림을 찾을 수 없습니다: " + notification_id));

    if(notification.getMember().getId() != member_id) throw (new RuntimeException("권한이 없습니다: " + member_id));


    notificationRepository.delete(notification);
  }
}

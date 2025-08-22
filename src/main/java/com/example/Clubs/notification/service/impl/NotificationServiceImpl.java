package com.example.Clubs.notification.service.impl;

import com.example.Clubs.member.entity.Member;
import com.example.Clubs.member.repository.MemberRepository;
import com.example.Clubs.notification.dto.request.CreateNotificationRequest;
import com.example.Clubs.notification.dto.request.UpdateNotificationRequest;
import com.example.Clubs.notification.dto.response.ReadNotificationResponse;
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
  public ReadNotificationResponse createNotification(CreateNotificationRequest request, long memberId) {

    Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다 : " + memberId));

    Notification notification =  request.toEntity(member);

    Notification result = notificationRepository.save(notification);

    return ReadNotificationResponse.from(result);

  }

  @Override
  public ReadNotificationResponse selectNotification(long notificationId) {

    Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new RuntimeException("알림을 찾을 수 없습니다: " + notificationId));

    return ReadNotificationResponse.from(notification);
  }

  @Override
  public List<ReadNotificationResponse> getAllNotifications() {
    return notificationRepository.findAll()
            .stream()
            .map(ReadNotificationResponse::from)
            .toList();
  }

  @Override
  public ReadNotificationResponse updateNotification(UpdateNotificationRequest request, long memberId, long notificationId) {

    notificationRepository.findById(notificationId)
            .orElseThrow(() -> new RuntimeException("알림을 찾을 수 없습니다: " + notificationId));

    Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다: " + memberId));

    if(memberId != member.getId()) throw(new RuntimeException("권한이 없습니다: " + memberId));

    Notification newNotification = Notification.builder()
            .id(notificationId)
            .member(member)
            .message(request.getMessage())
            .isActive(true)
            .build();

    return ReadNotificationResponse.from(notificationRepository.save(newNotification));
  }

  @Override
  public void deleteNotification(long notificationId, long memberId) {

    Notification notification = notificationRepository.findById(notificationId)
            .orElseThrow(() -> new RuntimeException("알림을 찾을 수 없습니다: " + notificationId));

    if(notification.getMember().getId() != memberId) throw (new RuntimeException("권한이 없습니다: " + memberId));


    notificationRepository.delete(notification);
  }
}

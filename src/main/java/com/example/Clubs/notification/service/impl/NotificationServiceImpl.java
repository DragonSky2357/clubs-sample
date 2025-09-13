package com.example.Clubs.notification.service.impl;

import com.example.Clubs.member.entity.Member;
import com.example.Clubs.member.repository.MemberRepository;
import com.example.Clubs.notification.dto.request.CreateNotificationRequest;
import com.example.Clubs.notification.dto.request.UpdateNotificationRequest;
import com.example.Clubs.notification.dto.response.ReadNotificationResponse;
import com.example.Clubs.notification.entity.Notification;
import com.example.Clubs.notification.repository.NotificationRepository;
import com.example.Clubs.notification.service.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

  private final MemberRepository memberRepository;
  private final NotificationRepository notificationRepository;

  @Transactional
  @Override
  public void createNotification(CreateNotificationRequest request, long memberId) {

    Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다 : " + memberId));

    Notification notification =  request.toEntity(member);

    notificationRepository.save(notification);
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

  @Transactional
  @Override
  public void updateNotification(UpdateNotificationRequest request, long memberId, long notificationId) {

     Notification notification = notificationRepository.findById(notificationId)
            .orElseThrow(() -> new RuntimeException("알림을 찾을 수 없습니다: " + notificationId));

    Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다: " + memberId));

    if(memberId != member.getId()) throw(new RuntimeException("권한이 없습니다: " + memberId));

    notificationRepository.save(notification);
  }

  @Transactional
  @Override
  public void deleteNotification(long notificationId, long memberId) {

    Notification notification = notificationRepository.findById(notificationId)
            .orElseThrow(() -> new RuntimeException("알림을 찾을 수 없습니다: " + notificationId));

    if(notification.getMember().getId() != memberId) throw (new RuntimeException("권한이 없습니다: " + memberId));


    notificationRepository.delete(notification);
  }
}

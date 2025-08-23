package com.example.Clubs.notification.service;


import com.example.Clubs.notification.dto.request.CreateNotificationRequest;
import com.example.Clubs.notification.dto.request.UpdateNotificationRequest;
import com.example.Clubs.notification.dto.response.ReadNotificationResponse;

import java.util.List;

public interface NotificationService {


  // 공고 생성(Create)
  void createNotification(CreateNotificationRequest request, long memberId);

  // 공고 조회(Select)
  ReadNotificationResponse selectNotification(long notificationId);

  // 전체 공고 조회(Select *)
  List<ReadNotificationResponse> getAllNotifications();

  // 공고 수정
  void updateNotification(UpdateNotificationRequest request, long memberId, long notificationId);

  // 공고 삭제
  void deleteNotification (long notificationId, long memberId);
}

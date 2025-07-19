package com.example.Clubs.notification.service;


import com.example.Clubs.notification.dto.request.CheckNotificationRequest;
import com.example.Clubs.notification.dto.response.CheckNotificationResponse;

import java.util.List;

public interface NotificationService {


  // 공고 생성(Create)
  CheckNotificationResponse createNotification(CheckNotificationRequest request);

  // 공고 조회(Select)
  CheckNotificationResponse selectNotification(long notification_id);

  // 전체 공고 조회(Select *)
  List<CheckNotificationResponse> getAllNotifications();

  // 공고 수정
  CheckNotificationResponse updateNotification(CheckNotificationRequest request, long notification_id);

  // 공고 삭제
  void deleteNotification (long notification_id);
}

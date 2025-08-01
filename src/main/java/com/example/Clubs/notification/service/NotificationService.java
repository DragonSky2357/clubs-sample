package com.example.Clubs.notification.service;


import com.example.Clubs.notification.dto.request.CheckNotificationRequest;
import com.example.Clubs.notification.dto.request.CreateNotificationRequest;
import com.example.Clubs.notification.dto.request.UpdateNotificationRequest;
import com.example.Clubs.notification.dto.response.CheckNotificationResponse;

import java.util.List;

public interface NotificationService {


  // 공고 생성(Create)
  CheckNotificationResponse createNotification(CreateNotificationRequest request, long member_id);

  // 공고 조회(Select)
  CheckNotificationResponse selectNotification(long notification_id);

  // 전체 공고 조회(Select *)
  List<CheckNotificationResponse> getAllNotifications();

  // 공고 수정
  CheckNotificationResponse updateNotification(UpdateNotificationRequest request, long member_id, long notification_id);

  // 공고 삭제
  void deleteNotification (long notification_id, long member_id);
}

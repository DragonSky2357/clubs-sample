package com.example.Clubs.notification.controller;

import com.example.Clubs.notification.dto.request.CheckNotificationRequest;
import com.example.Clubs.notification.dto.response.CheckNotificationResponse;
import com.example.Clubs.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

  private final NotificationService notificationService;

  // 공지 생성
  @PostMapping("/create")
  public CheckNotificationResponse createNotification(@RequestBody CheckNotificationRequest request){
    return notificationService.createNotification(request);
  }

  // 공지 조회
  @GetMapping("/check/{notification_id}")
  public CheckNotificationResponse checkNotification(@PathVariable long notification_id){
    return notificationService.selectNotification(notification_id);
  }

  // 전체 공지 조회
  @GetMapping("/check/all")
  public List<CheckNotificationResponse> getAllNotifications() {
    return notificationService.getAllNotifications();
  }

  // 공지 수정
  @PutMapping("/update/{notification_id}")
  public CheckNotificationResponse updateNotification(@RequestBody CheckNotificationRequest request, @PathVariable long notification_id){
    return notificationService.updateNotification(request, notification_id);
  }

  // 공지 삭제
  @DeleteMapping("/delete/{notification_id}")
  public ResponseEntity deleteNotification(@PathVariable long notification_id){
    notificationService.deleteNotification(notification_id);
    return ResponseEntity.ok().build();
  }

}


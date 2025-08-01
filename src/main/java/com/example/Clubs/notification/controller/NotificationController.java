package com.example.Clubs.notification.controller;

import com.example.Clubs.config.security.entity.User;
import com.example.Clubs.notification.dto.request.CheckNotificationRequest;
import com.example.Clubs.notification.dto.request.CreateNotificationRequest;
import com.example.Clubs.notification.dto.request.UpdateNotificationRequest;
import com.example.Clubs.notification.dto.response.CheckNotificationResponse;
import com.example.Clubs.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

  private final NotificationService notificationService;

  // 공지 생성(메시지, 유저)
  @PostMapping("/create")
  public CheckNotificationResponse createNotification(@RequestBody CreateNotificationRequest request, @AuthenticationPrincipal User user){
    return notificationService.createNotification(request, user.getId());
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
  @PutMapping("/{notification_id}")
  public CheckNotificationResponse updateNotification(@RequestBody UpdateNotificationRequest request, @AuthenticationPrincipal User user, @PathVariable long notification_id){
    return notificationService.updateNotification(request, user.getId(), notification_id);
  }

  // 공지 삭제
  @DeleteMapping("/{notification_id}")
  public ResponseEntity deleteNotification(@PathVariable long notification_id, @AuthenticationPrincipal User user){
    notificationService.deleteNotification(notification_id, user.getId());
    return ResponseEntity.ok().build();
  }

}


package com.example.Clubs.notification.controller;

import com.example.Clubs.config.security.entity.User;
import com.example.Clubs.notification.dto.request.CreateNotificationRequest;
import com.example.Clubs.notification.dto.request.UpdateNotificationRequest;
import com.example.Clubs.notification.dto.response.ReadNotificationResponse;
import com.example.Clubs.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notification")
public class NotificationController {

  private final NotificationService notificationService;

  // 공지 생성(메시지, 유저)
  @PostMapping("")
  public ResponseEntity createNotification(@RequestBody CreateNotificationRequest request, @AuthenticationPrincipal User user){
    notificationService.createNotification(request, user.getId());
    return ResponseEntity.ok().build();
  }

  // 공지 조회
  @GetMapping("/{notificationId}")
  public ReadNotificationResponse readNotification(@PathVariable Long notificationId){
    return notificationService.selectNotification(notificationId);
  }

  // 전체 공지 조회
  @GetMapping("/all")
  public List<ReadNotificationResponse> getAllNotifications() {
    return notificationService.getAllNotifications();
  }

  // 공지 수정
  @PutMapping("/{notificationId}")
  public ResponseEntity updateNotification(@RequestBody UpdateNotificationRequest request, @AuthenticationPrincipal User user, @PathVariable Long notificationId){
    notificationService.updateNotification(request, user.getId(), notificationId);
    return ResponseEntity.ok().build();
  }

  // 공지 삭제
  @DeleteMapping("/{notificationId}")
  public ResponseEntity deleteNotification(@PathVariable Long notificationId, @AuthenticationPrincipal User user){
    notificationService.deleteNotification(notificationId, user.getId());
    return ResponseEntity.ok().build();
  }

}


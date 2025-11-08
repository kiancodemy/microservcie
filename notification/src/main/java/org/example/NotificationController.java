package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.clients.notification.NotificationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@Slf4j
@RequiredArgsConstructor
public final class NotificationController {
    private final NotificationRepository notificationRepository;


    @PostMapping("/send")
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
        NotificationModel notificationModel = NotificationModel.builder()
                .senderEmail(notificationRequest.customerEmail()).customerId(notificationRequest.customerId())
                .created(notificationRequest.sentAt())
                .message(notificationRequest.message())
                .senderName(notificationRequest.sender())
                .build();
        notificationRepository.save(notificationModel);
        log.info("Notification sent successfully");


    }
}

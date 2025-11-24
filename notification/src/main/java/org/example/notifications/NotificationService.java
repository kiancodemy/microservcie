package org.example.notifications;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.clients.notification.NotificationRequest;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest notificationRequest) {
        NotificationModel notificationModel = NotificationModel.builder()
                .senderEmail(notificationRequest.customerEmail()).customerId(notificationRequest.customerId())
                .created(notificationRequest.sentAt())
                .message(notificationRequest.message())
                .senderName(notificationRequest.sender())
                .build();
        notificationRepository.save(notificationModel);
        log.info("Notification sent successfully  to id {}", notificationModel.getId());


    }


}

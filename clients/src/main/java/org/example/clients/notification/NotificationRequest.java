package org.example.clients.notification;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record NotificationRequest(LocalDateTime sentAt,String sender,String message,String customerEmail,Long customerId) {
}

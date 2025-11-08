package org.example.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="notification" )
public interface NotificationClient {

    @PostMapping("/notification/send")
    void sendNotification(@RequestBody NotificationRequest notificationRequest);
}

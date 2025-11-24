package org.example.notifications.rabbitMq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.clients.notification.NotificationRequest;
import org.example.notifications.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notif}")
    public void consumer(NotificationRequest notificationRequest){
        log.info("it is message is {}", notificationRequest.message());
        notificationService.sendNotification(notificationRequest);
        log.info("it is recieved by notification");

    }
}

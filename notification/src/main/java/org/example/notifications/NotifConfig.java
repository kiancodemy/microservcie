package org.example.notifications;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class NotifConfig {

    @Value("${rabbitmq.exchange.internal}")
    private String notificationExchange;

    @Value("${rabbitmq.queue.notif}")
    private String notificationQueue;

    @Value("${rabbitmq.routing-keys.int}")
    private String notificationKey;

    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange(this.notificationExchange);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(this.notificationQueue);
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(notificationQueue)
                .to(notificationExchange)
                .with(this.notificationKey);
    }
}

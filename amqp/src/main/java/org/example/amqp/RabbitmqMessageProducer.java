package org.example.amqp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class RabbitmqMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public void publish(Object payload,String exchange, String routingKey){
        log.info("start sending,exchange is {},and key is {} and payload {}",exchange,routingKey,payload);
        rabbitTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("finished sending,exchange is {},and key is {}",exchange,routingKey);
    }
}

package org.example.notifications;

import org.example.amqp.RabbitmqMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages= {"org.example.amqp","org.example.notifications"})
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner (RabbitmqMessageProducer rabbitmqMessageProducer, NotifConfig notifConfig){

        return args -> {
            rabbitmqMessageProducer.publish("foo",notifConfig.getNotificationExchange(),notifConfig.getNotificationKey());
        };}

}
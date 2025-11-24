package com.example;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.amqp.RabbitmqMessageProducer;
import org.example.clients.fraud.FraudCheckResponse;
import org.example.clients.fraud.FraudClient;
import org.example.clients.notification.NotificationClient;
import org.example.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitmqMessageProducer rabbitmqMessageProducer;


    public void register(CustomerRequest customerRequest) {

        Customer newCustomer=Customer.builder().first(customerRequest.first()).last(customerRequest.last()).email(customerRequest.email()).build();

        customerRepository.saveAndFlush(newCustomer);
        log.info("it is send to fraud {}",newCustomer.getId());
        FraudCheckResponse fraud = fraudClient.isFraud(newCustomer.getId());


        if(fraud.isFraud()){
            throw new RuntimeException("you are fraud");
        }
        String message="salam it is ok";
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .customerEmail(customerRequest.email())
                .customerId(newCustomer.getId())
                .message(message)
                .sender(customerRequest.first())
                .sentAt(LocalDateTime.now())
                .build();
        ///notificationClient.sendNotification(notificationRequest);
        rabbitmqMessageProducer.publish(notificationRequest,"internal-exchange","internal-key");
    }
}

package com.example;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.clients.FraudCheckResponse;
import org.example.clients.FraudClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final RestTemplate restTemplate;
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;


    public void register(CustomerRequest customerRequest) {

        Customer newCustomer=Customer.builder().first(customerRequest.first()).last(customerRequest.last()).email(customerRequest.email()).build();

        customerRepository.saveAndFlush(newCustomer);
        FraudCheckResponse fraud = fraudClient.isFraud(newCustomer.getId());


        if(fraud.isFraud()){
            throw new RuntimeException("you are fraud");
        }




    }
}

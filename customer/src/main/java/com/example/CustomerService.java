package com.example;
import farud.example.FraudCheckResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final RestTemplate restTemplate;
    private final CustomerRepository customerRepository;


    public void register(CustomerRequest customerRequest) {
        String url = "http://localhost:9091/api/fraud/{customerId}";
        Customer newCustomer=Customer.builder().first(customerRequest.first()).last(customerRequest.last()).email(customerRequest.email()).build();

        customerRepository.saveAndFlush(newCustomer);

        FraudCheckResponse response = restTemplate.getForObject(
                url,
                FraudCheckResponse.class,
                newCustomer.getId());

        log.info("customer id is send to fraud");
        if(response.isFraud()){
            throw new RuntimeException("you are fraud");
        }




    }
}

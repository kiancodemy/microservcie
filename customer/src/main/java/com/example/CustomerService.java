package com.example;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;


    public void register(CustomerRequest customerRequest) {
        Customer newCustomer=Customer.builder().first(customerRequest.first()).last(customerRequest.last()).email(customerRequest.email()).build();


    }
}

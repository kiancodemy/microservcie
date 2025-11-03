package org.example.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="fraud" )
public interface FraudClient {
    @GetMapping("/api/fraud/{customerId}")
    FraudCheckResponse isFraud(@PathVariable("customerId") Long customerId);
}

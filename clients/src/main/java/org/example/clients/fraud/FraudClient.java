package org.example.clients.fraud;

import org.example.clients.FeignTracingConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "fraud",configuration = FeignTracingConfig.class)


public interface FraudClient {
    @GetMapping("/api/fraud/{customerId}")
    FraudCheckResponse isFraud(@PathVariable("customerId") Long customerId);
}

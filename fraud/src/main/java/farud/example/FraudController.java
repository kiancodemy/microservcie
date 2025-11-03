package farud.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.clients.FraudCheckResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fraud")
@Slf4j
public class FraudController {
    private final FraudService fraudService;

    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraud(@PathVariable("customerId") Long customerId){
        boolean isFraud = fraudService.isFraudCustomer(customerId);
        log.info("fraud data is saved and send to customer service for customer id which is "+customerId);
        return new FraudCheckResponse(isFraud);

    }
}

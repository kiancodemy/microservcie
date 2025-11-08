package farud.example;

import lombok.RequiredArgsConstructor;
import org.example.clients.notification.NotificationClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudService {
    private final FraudRepository  fraudRepository;


    public boolean isFraudCustomer(Long customerId) {
        fraudRepository.save(FraudCheckHistoryModel.builder().isFraud(false).created(LocalDateTime.now()).customerId(customerId).build());
        return false;
    }


}

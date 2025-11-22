package farud.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class FraudService {
    private final FraudRepository  fraudRepository;


    public boolean isFraudCustomer(Long customerId) {
        fraudRepository.save(FraudCheckHistoryModel.builder().isFraud(false).created(LocalDateTime.now()).customerId(customerId).build());
        return false;
    }


}

package dev.akibaz.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Integer customerId) {
        // Some service checked this for us
        boolean isFraudulent = false;
        // We want the record of this check in db
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(isFraudulent)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return isFraudulent;
    }
}

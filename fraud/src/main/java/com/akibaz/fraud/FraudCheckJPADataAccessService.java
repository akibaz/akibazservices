package com.akibaz.fraud;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class FraudCheckJPADataAccessService implements FraudCheckDAO {

    private final FraudCheckRepository fraudCheckRepository;

    public FraudCheckJPADataAccessService(FraudCheckRepository fraudCheckRepository) {
        this.fraudCheckRepository = fraudCheckRepository;
    }

    @Override
    public void insertFraudCheck(Integer customerId) {
        fraudCheckRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }
}

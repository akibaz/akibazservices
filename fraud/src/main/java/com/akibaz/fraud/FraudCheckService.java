package com.akibaz.fraud;

import org.springframework.stereotype.Service;

@Service
public record FraudCheckService(FraudCheckDAO fraudCheckDAO) {
    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckDAO.insertFraudCheck(customerId);
        return false;
    }
}

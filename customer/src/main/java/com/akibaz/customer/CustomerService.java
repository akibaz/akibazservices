package com.akibaz.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerDAO customerDAO;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest registrationRequest) {
        Customer newCustomer = Customer.builder()
                .firstName(registrationRequest.firstName())
                .lastName(registrationRequest.lastName())
                .email(registrationRequest.email())
                .build();
        System.out.println("newCustomer = " + newCustomer);
        // todo: check if email is valid
        // todo: check if email not taken
        customerDAO.insertCustomer(newCustomer);
        // todo: check if customer is fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                newCustomer.getId()
        );

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster.");
        }

        // todo: send notification
    }
}

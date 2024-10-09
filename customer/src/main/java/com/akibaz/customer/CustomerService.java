package com.akibaz.customer;

import com.akibaz.clients.fraud.FraudCheckResponse;
import com.akibaz.clients.fraud.FraudClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerDAO customerDAO;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

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
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                newCustomer.getId()
//        );
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(newCustomer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster.");
        }

        // todo: send notification
    }
}

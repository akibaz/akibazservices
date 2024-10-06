package com.akibaz.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public void registerCustomer(CustomerRegistrationRequest registrationRequest) {
        Customer newCustomer = Customer.builder()
                .firstName(registrationRequest.firstName())
                .lastName(registrationRequest.lastName())
                .email(registrationRequest.email())
                .build();
        System.out.println("newCustomer = " + newCustomer);
    }
}

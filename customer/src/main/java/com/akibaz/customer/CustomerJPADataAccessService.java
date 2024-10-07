package com.akibaz.customer;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerJPADataAccessService implements CustomerDAO {
    private final CustomerRepository customerRepository;

    public CustomerJPADataAccessService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void insertCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}

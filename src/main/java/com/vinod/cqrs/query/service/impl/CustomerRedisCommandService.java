package com.vinod.cqrs.query.service.impl;

import com.vinod.cqrs.query.model.Customer;
import com.vinod.cqrs.query.repository.CustomerRepository;
import com.vinod.cqrs.query.service.ICustomerRedisCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerRedisCommandService implements ICustomerRedisCommandService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void addCustomer(Customer customer) {
        log.trace("Request came to add new customer to redis: {}", customer);
        customerRepository.save(customer);
    }
}

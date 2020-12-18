package com.vinod.cqrs.query.service;

import com.vinod.cqrs.query.model.Customer;

public interface ICustomerRedisCommandService {

    /**
     * Add customer object to database.
     *
     * @param customer - Customer command object.
     */
    void addCustomer(Customer customer);
}


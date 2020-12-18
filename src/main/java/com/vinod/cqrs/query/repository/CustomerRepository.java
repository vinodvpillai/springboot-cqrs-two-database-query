package com.vinod.cqrs.query.repository;

import com.vinod.cqrs.query.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {}


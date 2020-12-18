package com.vinod.cqrs.query.service.impl;

import com.vinod.cqrs.query.dto.CustomerQueryDto;
import com.vinod.cqrs.query.model.Customer;
import com.vinod.cqrs.query.repository.CustomerRepository;
import com.vinod.cqrs.query.service.ICustomerRedisQueryService;
import com.vinod.cqrs.query.util.GlobalUtility;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomerRedisQueryService implements ICustomerRedisQueryService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get customer object by ID.
     *
     * @param id    - Customer ID.
     * @return      - Customer Query object.
     */
    @Override
    public CustomerQueryDto getCustomerById(Long id) {
        log.trace("Request came to get customer details for customer id: {}", id);
        Optional<Customer> optionalCustomer=customerRepository.findById(GlobalUtility.formatCustomerId(String.valueOf(id),'C',8));
        return optionalCustomer.isPresent() ? mapDataToCustomerCreatedEventData(optionalCustomer.get()) : null;
    }

    /**
     * Map CustomerRegisterCommandDto to Customer object.
     *
     * @param customer - CustomerRegisterCommandDto object.
     * @return  - Customer Query object.
     */
    private CustomerQueryDto mapDataToCustomerCreatedEventData(Customer customer) {
        modelMapper.typeMap(Customer.class, CustomerQueryDto.class).addMappings(mapper -> mapper.skip(CustomerQueryDto::setId));
        CustomerQueryDto customerQueryDto = modelMapper.map(customer, CustomerQueryDto.class);
        customerQueryDto.setId(GlobalUtility.reformatCustomerId(customer.getId()));
        return customerQueryDto;
    }
}

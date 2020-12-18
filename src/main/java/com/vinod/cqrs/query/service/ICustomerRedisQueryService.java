package com.vinod.cqrs.query.service;

import com.vinod.cqrs.query.dto.CustomerQueryDto;

public interface ICustomerRedisQueryService {

    /**
     * Get customer object by customer id.
     *
     * @param id    - Customer ID.
     * @return      - Customer query object.
     */
    CustomerQueryDto getCustomerById(Long id);
}

package com.vinod.cqrs.query.controller;

import com.vinod.cqrs.query.dto.CustomerQueryDto;
import com.vinod.cqrs.query.service.ICustomerRedisQueryService;
import com.vinod.cqrs.query.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.vinod.cqrs.query.util.GlobalUtility.buildResponseForError;
import static com.vinod.cqrs.query.util.GlobalUtility.buildResponseForSuccess;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerQueryRestController {

    @Autowired
    private ICustomerRedisQueryService customerRedisQueryService;

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCustomer(@PathVariable("id") Long id) {
        log.trace("Request came to get the customer details having the id: {}", id);
        CustomerQueryDto customerQueryDto=customerRedisQueryService.getCustomerById(id);
        if(null!=customerQueryDto) {
            return buildResponseForSuccess(HttpStatus.SC_OK,"Successfully fetched customer",customerQueryDto);
        }
        return buildResponseForError(HttpStatus.SC_BAD_REQUEST, String.valueOf(HttpStatus.SC_BAD_REQUEST),"No customer detail found for the given id.",null);
    }

}

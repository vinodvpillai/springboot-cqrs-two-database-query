package com.vinod.cqrs.query.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinod.cqrs.query.model.Customer;
import com.vinod.cqrs.query.service.ICustomerRedisCommandService;
import com.vinod.cqrs.query.util.GlobalUtility;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CustomerHandler {
    @Value("${sqs.queue.customer.created}")
    private String sqsCustomerCreated;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ICustomerRedisCommandService iCustomerRedisCommandService;
    private ObjectMapper objectMapper= GlobalUtility.getDateFormatObjectMapper();

    @JmsListener(destination = "${sqs.queue.customer.created}")
    void whenCustomerCreated(String message) throws Exception {
        log.trace("Event received that a customer is created");
        JsonNode jsonMessage= GlobalUtility.isNotNull(objectMapper.readTree(message)) ? objectMapper.readTree(message).get("Message"):null;
        if(GlobalUtility.isNotNull(jsonMessage)){
            Customer customer=objectMapper.readValue(jsonMessage.asText(),Customer.class);
            log.trace("Received customer created object: {}",customer);
            iCustomerRedisCommandService.addCustomer(customer);
        } else {
            log.warn("No message found in the message for: {} and the message: {}"+ sqsCustomerCreated, message);
        }
    }
}

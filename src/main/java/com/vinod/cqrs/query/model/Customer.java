package com.vinod.cqrs.query.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Customer")
public class Customer implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private String emailId;
    private boolean enabled;

}
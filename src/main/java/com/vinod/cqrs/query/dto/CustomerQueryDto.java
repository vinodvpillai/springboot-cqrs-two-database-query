package com.vinod.cqrs.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerQueryDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
}

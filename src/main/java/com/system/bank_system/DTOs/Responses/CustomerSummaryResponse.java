package com.system.bank_system.DTOs.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerSummaryResponse {
    private Long customer_id;
    private String firstName;
    private String lastName;
    private UserResponse user;
}
package com.system.bank_system.DTOs.Requests.customers;

import com.system.bank_system.DTOs.Requests.User.UpdateUserRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String address;
    private UpdateUserRequest user;
}
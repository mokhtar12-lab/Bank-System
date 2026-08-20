package com.system.bank_system.Services.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.bank_system.DTOs.Requests.customers.UpdateCustomerRequest;
import com.system.bank_system.DTOs.Responses.CustomerResponse;


public interface CustomerService {
    Page<CustomerResponse> getAllCustomers( Pageable pageable );

    CustomerResponse getCustomerById(Long id);

    CustomerResponse updateCustomer(UpdateCustomerRequest request);

    void deleteCustomer(Long id);
}

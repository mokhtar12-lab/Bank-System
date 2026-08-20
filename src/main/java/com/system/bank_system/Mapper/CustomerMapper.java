package com.system.bank_system.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.system.bank_system.DTOs.Requests.customers.CustomerRequest;
import com.system.bank_system.DTOs.Requests.customers.UpdateCustomerRequest;
import com.system.bank_system.DTOs.Responses.AuthenticationResponse;
import com.system.bank_system.DTOs.Responses.CustomerResponse;
import com.system.bank_system.Models.Customer;

@Mapper(componentModel="spring", uses=UserMapper.class , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    Customer toEntity(CustomerRequest request);

    CustomerResponse toResponse(Customer customer);

    AuthenticationResponse toRegisterResponse(Customer customer);

    Customer updateCustomerResponse(UpdateCustomerRequest request, @MappingTarget Customer customer);

    @Mapping(target = "userRole", source = "user.userRole")
    List<CustomerResponse> toResponseArray(List<Customer> customers);
}
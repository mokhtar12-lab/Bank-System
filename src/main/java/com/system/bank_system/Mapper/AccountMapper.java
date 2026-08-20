package com.system.bank_system.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.system.bank_system.DTOs.Requests.Account.CreateAccountRequest;
import com.system.bank_system.DTOs.Responses.AccountResponse;
import com.system.bank_system.Models.Account;

@Mapper(componentModel="spring", uses=CustomerMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    @Mapping(target= "accountId", ignore=true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "accountStatus", ignore = true)
    Account toEntity( CreateAccountRequest request );

    AccountResponse toResponse( Account account );
}
package com.system.bank_system.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.system.bank_system.DTOs.Requests.Transactions.DepositRequest;
import com.system.bank_system.DTOs.Requests.Transactions.TransferRequest;
import com.system.bank_system.DTOs.Requests.Transactions.WithdrawRequest;
import com.system.bank_system.DTOs.Responses.TransactionResponse;
import com.system.bank_system.Models.Transaction;

@Mapper(componentModel="spring", uses={AccountMapper.class} ,unmappedTargetPolicy=ReportingPolicy.IGNORE)
public interface TransactionMapper {
    @Mapping(target = "transactionId", ignore = true)
    @Mapping(target = "fromAccount", ignore = true)
    @Mapping(target = "toAccount", ignore = true)
    @Mapping(target = "transactionStatus", ignore = true)
    @Mapping(target = "transactionType", ignore = true)
    @Mapping(target = "transactionDate", ignore = true)
    Transaction toDeposit(DepositRequest depositRequest);

    @Mapping(target = "transactionId", ignore = true)
    @Mapping(target = "fromAccount", ignore = true)
    @Mapping(target = "toAccount", ignore = true)
    @Mapping(target = "transactionStatus", ignore = true)
    @Mapping(target = "transactionType", ignore = true)
    @Mapping(target = "transactionDate", ignore = true)
    Transaction toWithdraw(WithdrawRequest withdrawRequest);

    @Mapping(target = "transactionId", ignore = true)
    @Mapping(target = "fromAccount", ignore = true)
    @Mapping(target = "toAccount", ignore = true)
    @Mapping(target = "transactionStatus", ignore = true)
    @Mapping(target = "transactionType", ignore = true)
    @Mapping(target = "transactionDate", ignore = true)
    Transaction toTransfer(TransferRequest transferRequest);

    TransactionResponse toResponse(Transaction transaction);

    // Page<TransactionResponse> toResponseArray(List<Transaction> transactions);
}
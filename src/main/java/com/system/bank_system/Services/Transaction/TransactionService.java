package com.system.bank_system.Services.Transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.bank_system.DTOs.Requests.Transactions.DepositRequest;
import com.system.bank_system.DTOs.Requests.Transactions.TransferRequest;
import com.system.bank_system.DTOs.Requests.Transactions.WithdrawRequest;
import com.system.bank_system.DTOs.Responses.TransactionResponse;
import com.system.bank_system.enums.TransactionType;

public interface TransactionService {
    TransactionResponse deposit(DepositRequest request);

    TransactionResponse withdraw(WithdrawRequest request);

    TransactionResponse transfer(TransferRequest request);

    TransactionResponse getTransactionById(Long transactionId);

    Page<TransactionResponse> getAllTransactions(Pageable pageable);

    Page<TransactionResponse> getAllTransfers(Pageable pageable);

    Page<TransactionResponse> getAccountTransactions( Pageable pageable, Long accountId);

    Page<TransactionResponse> getByTransactionType(Pageable pageable, TransactionType type);
}

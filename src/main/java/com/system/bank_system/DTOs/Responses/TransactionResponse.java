package com.system.bank_system.DTOs.Responses;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.system.bank_system.enums.TransactionStatus;
import com.system.bank_system.enums.TransactionType;

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
public class TransactionResponse {
    private Long transactionId;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private BigDecimal amount;
    private String description;
    private LocalDate transactionDate;
    private AccountResponse fromAccount;
    private AccountResponse toAccount;
}
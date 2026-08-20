package com.system.bank_system.DTOs.Responses;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.system.bank_system.enums.AccountStatus;
import com.system.bank_system.enums.AccountType;

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
public class AccountSummaryResponse {
    private Long accountId;
    private String accountNumber;
    private BigDecimal balance;
    private LocalDate createdAt;
    private AccountType accountType;
    private AccountStatus accountStatus;
}
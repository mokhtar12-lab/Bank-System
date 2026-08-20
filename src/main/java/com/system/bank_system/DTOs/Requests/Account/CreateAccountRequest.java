package com.system.bank_system.DTOs.Requests.Account;

import java.math.BigDecimal;

import com.system.bank_system.enums.AccountType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CreateAccountRequest {
    private Long accountId;
    @NotBlank @NotNull
    private String accountNumber;
    @NotNull
    private BigDecimal balance;
    private AccountType accountType;
    private Long customer_id;
}
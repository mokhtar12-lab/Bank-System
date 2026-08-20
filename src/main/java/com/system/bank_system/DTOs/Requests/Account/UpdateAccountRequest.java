package com.system.bank_system.DTOs.Requests.Account;

import com.system.bank_system.enums.AccountStatus;
import com.system.bank_system.enums.AccountType;

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
public class UpdateAccountRequest {
    private Long accountId;

    @NotNull
    private AccountStatus accountStatus;

    @NotNull
    private AccountType accountType;
}

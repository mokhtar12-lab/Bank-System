package com.system.bank_system.DTOs.Requests.Transactions;
import java.math.BigDecimal;

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
public class DepositRequest {
    private Long accountId;
    @NotNull
    private BigDecimal amount;
    private String description;
}
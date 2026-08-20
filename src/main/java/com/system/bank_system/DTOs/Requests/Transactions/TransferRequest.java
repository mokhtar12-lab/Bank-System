package com.system.bank_system.DTOs.Requests.Transactions;
import java.math.BigDecimal;

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
public class TransferRequest {
    private Long fromAccount;
    private Long toAccount;
    private BigDecimal amount;
    private String description;
}
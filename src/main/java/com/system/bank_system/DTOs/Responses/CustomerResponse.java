package com.system.bank_system.DTOs.Responses;
import java.time.LocalDate;
import java.util.List;

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
public class CustomerResponse {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String nationalId;
    private String address;
    private LocalDate dateOfBirth;
    private UserResponse user;
    private List<AccountSummaryResponse> accounts;
}

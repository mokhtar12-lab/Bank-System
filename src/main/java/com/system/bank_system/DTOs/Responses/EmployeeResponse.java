package com.system.bank_system.DTOs.Responses;
import java.math.BigDecimal;
import java.time.LocalDate;

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
public class EmployeeResponse {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private BigDecimal salary;
    private LocalDate hireDate;
    private UserResponse user;
}

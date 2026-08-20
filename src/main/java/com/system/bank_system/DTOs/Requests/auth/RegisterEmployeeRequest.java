package com.system.bank_system.DTOs.Requests.auth;
import com.system.bank_system.DTOs.Requests.employees.EmployeeRequest;
import com.system.bank_system.enums.UserRole;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class RegisterEmployeeRequest {
    @NotBlank
    private String userName;
    @Email @NotBlank
    private String email;
    @NotBlank
    @Size(min=8)
    private String password;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private UserRole userRole;

    @Valid
    @NotNull
    private EmployeeRequest employee;
}

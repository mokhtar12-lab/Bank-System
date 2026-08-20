package com.system.bank_system.DTOs.Requests.User;

import com.system.bank_system.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class RegisterUserRequest {
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
}
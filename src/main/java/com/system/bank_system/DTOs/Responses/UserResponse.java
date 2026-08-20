package com.system.bank_system.DTOs.Responses;

import com.system.bank_system.enums.UserRole;

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
public class UserResponse {
    private Long user_id;
    private String userName;
    private String email;
    private String phoneNumber;
    private UserRole userRole;
}

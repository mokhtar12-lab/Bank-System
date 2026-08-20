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
public class AuthenticationSummaryResponse {
    private String token;

    @Builder.Default
    private String type = "Bearer";

    private String username;

    private String email;

    private UserRole role;
}

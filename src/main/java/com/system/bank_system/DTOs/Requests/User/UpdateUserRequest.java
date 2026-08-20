package com.system.bank_system.DTOs.Requests.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
}
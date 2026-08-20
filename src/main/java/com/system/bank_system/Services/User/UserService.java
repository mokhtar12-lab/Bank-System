package com.system.bank_system.Services.User;

import com.system.bank_system.DTOs.Requests.User.RegisterUserRequest;
import com.system.bank_system.DTOs.Responses.UserResponse;

public interface UserService {
    UserResponse registerAdmin(RegisterUserRequest request);
}
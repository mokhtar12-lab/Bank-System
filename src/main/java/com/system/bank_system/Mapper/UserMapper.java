package com.system.bank_system.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.system.bank_system.DTOs.Requests.User.RegisterUserRequest;
import com.system.bank_system.DTOs.Requests.User.UpdateUserRequest;
import com.system.bank_system.DTOs.Requests.auth.LoginRequest;
import com.system.bank_system.DTOs.Requests.auth.RegisterCustomerRequest;
import com.system.bank_system.DTOs.Requests.auth.RegisterEmployeeRequest;
import com.system.bank_system.DTOs.Responses.AuthenticationResponse;
import com.system.bank_system.DTOs.Responses.AuthenticationSummaryResponse;
import com.system.bank_system.DTOs.Responses.UserResponse;
import com.system.bank_system.Models.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User toAdminEntity(RegisterUserRequest request);

    User toEntity(RegisterCustomerRequest request);

    User toEntity(RegisterEmployeeRequest request);

    User updateUserResponse(UpdateUserRequest request, @MappingTarget User user);

    AuthenticationSummaryResponse toLogInResponse(LoginRequest login);

    UserResponse toResponse(User user);

    AuthenticationResponse toRegisterResponse(User user);

    default User updateUser(UpdateUserRequest request, User user) {
        return updateUserResponse(request, user);
    }

}
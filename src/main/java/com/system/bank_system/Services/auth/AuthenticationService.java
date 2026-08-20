package com.system.bank_system.Services.auth;

import com.system.bank_system.DTOs.Requests.User.RegisterUserRequest;
import com.system.bank_system.DTOs.Requests.auth.LoginRequest;
import com.system.bank_system.DTOs.Requests.auth.RegisterCustomerRequest;
import com.system.bank_system.DTOs.Requests.auth.RegisterEmployeeRequest;
import com.system.bank_system.DTOs.Responses.AuthenticationResponse;
import com.system.bank_system.DTOs.Responses.AuthenticationSummaryResponse;

public interface AuthenticationService {

    AuthenticationResponse registerCustomer( RegisterCustomerRequest request );

    AuthenticationResponse registerEmployee( RegisterEmployeeRequest request );

    AuthenticationResponse registerAdmin( RegisterUserRequest request );

    AuthenticationSummaryResponse login( LoginRequest request );
}

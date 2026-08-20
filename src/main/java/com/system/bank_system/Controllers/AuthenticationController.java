package com.system.bank_system.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.bank_system.DTOs.Requests.User.RegisterUserRequest;
import com.system.bank_system.DTOs.Requests.auth.LoginRequest;
import com.system.bank_system.DTOs.Requests.auth.RegisterCustomerRequest;
import com.system.bank_system.DTOs.Requests.auth.RegisterEmployeeRequest;
import com.system.bank_system.DTOs.Responses.AuthenticationSummaryResponse;
import com.system.bank_system.Services.auth.Imp.AuthenticationServiceImp;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationServiceImp authenticationServiceImp;

    @PostMapping("/registerCustomer")
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterCustomerRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationServiceImp.registerCustomer(request));
    }

    @PostMapping("/registerEmployee")
    public ResponseEntity<?> registerEmployee(@RequestBody RegisterEmployeeRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationServiceImp.registerEmployee(request));
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterUserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationServiceImp.registerAdmin(request));
    }

    @PostMapping("/logIn")
    public ResponseEntity<AuthenticationSummaryResponse> logIn(@RequestBody @Valid LoginRequest request){
        return ResponseEntity.ok( authenticationServiceImp.login(request) );
    }
}
package com.system.bank_system.Services.auth.Imp;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.bank_system.DTOs.Requests.User.RegisterUserRequest;
import com.system.bank_system.DTOs.Requests.auth.LoginRequest;
import com.system.bank_system.DTOs.Requests.auth.RegisterCustomerRequest;
import com.system.bank_system.DTOs.Requests.auth.RegisterEmployeeRequest;
import com.system.bank_system.DTOs.Responses.AuthenticationResponse;
import com.system.bank_system.DTOs.Responses.AuthenticationSummaryResponse;
import com.system.bank_system.Errors.RecordNotFound;
import com.system.bank_system.Mapper.CustomerMapper;
import com.system.bank_system.Mapper.EmployeeMapper;
import com.system.bank_system.Mapper.UserMapper;
import com.system.bank_system.Models.Customer;
import com.system.bank_system.Models.Employee;
import com.system.bank_system.Models.User;
import com.system.bank_system.Repositories.UserRepo;
import com.system.bank_system.Security.JWTService;
import com.system.bank_system.Security.SecUserDetails;
import com.system.bank_system.Services.auth.AuthenticationService;
import com.system.bank_system.Validator.Validation;
import com.system.bank_system.enums.UserRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final CustomerMapper customerMapper;
    private final EmployeeMapper employeeMapper;
    private final Validation validation;
    private final JWTService jWTService;
    private final PasswordEncoder passwordEncoder;


    // Methods For Checking
    private AuthenticationResponse generateToken(User user){
        var jwtToken = jWTService.generateToken( new SecUserDetails(user) );
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

        // Customer Method
    private User mapUser(RegisterCustomerRequest request){
        User user = userMapper.toEntity(request);
        user.setUserRole(UserRole.ROLE_CUSTOMER);
        user.setPassword( passwordEncoder.encode(request.getPassword()) );
        return user;
    }
    private Customer mapCustomer(RegisterCustomerRequest request){
        Customer customer = customerMapper.toEntity(request.getCustomer());
        return customer;
    }
    private void linkUserAndCustomer(User user, Customer customer) {
        user.setCustomer(customer);
    }
    private User save(User user) {
        return userRepo.save(user);
    }

        // Employee Method
    private User mapEmpUser(RegisterEmployeeRequest request){
        User user = userMapper.toEntity(request);
        user.setUserRole(UserRole.ROLE_EMPLOYEE);
        user.setPassword( passwordEncoder.encode(request.getPassword()) );
        return user;
    }
    private Employee mapEmployee(RegisterEmployeeRequest request){
        Employee customer = employeeMapper.toEntity(request.getEmployee());
        return customer;
    }
    private void linkUserAndEmployee(User user, Employee emp) {
        user.setEmployee(emp);
    }
    private User saveUser(User user) {
        return userRepo.save(user);
    }
    // ---------------------------

    @Override
    public AuthenticationResponse registerCustomer(RegisterCustomerRequest request) {
        validation.EmailIsExists(request.getEmail());
        validation.NationalIdIsExists(request.getCustomer().getNationalId());
        User user =  mapUser(request);
        Customer customer = mapCustomer(request);
        linkUserAndCustomer(user, customer);
        save(user);
        return generateToken(user);
    }

    @Override
    public AuthenticationResponse registerEmployee(RegisterEmployeeRequest request) {
        validation.EmailIsExists(request.getEmail());
        User user = mapEmpUser(request);
        Employee emp = mapEmployee(request);
        linkUserAndEmployee(user, emp);
        saveUser(user);
        return generateToken(user);  
    }

    @Override
    public AuthenticationResponse registerAdmin(RegisterUserRequest request) {
        validation.EmailIsExists(request.getEmail());
        User admin = userMapper.toAdminEntity(request);
        admin.setUserRole(UserRole.ROLE_ADMIN);
        admin.setPassword( passwordEncoder.encode(request.getPassword()) );
        userRepo.save(admin);
        return generateToken(admin);
    }

    @Override
    public AuthenticationSummaryResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepo.findByEmail(request.getEmail())
                    .orElseThrow( ()-> new RecordNotFound("User Not Found") );
        String token = jWTService.generateToken( new SecUserDetails(user) );
        return AuthenticationSummaryResponse.builder()
                        .token(token)
                        .username(user.getUserName())
                        .email(user.getEmail())
                        .role(user.getUserRole())
                        .build();
    }
}
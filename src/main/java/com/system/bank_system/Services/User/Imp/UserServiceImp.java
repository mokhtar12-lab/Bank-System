package com.system.bank_system.Services.User.Imp;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.bank_system.DTOs.Requests.User.RegisterUserRequest;
import com.system.bank_system.DTOs.Responses.UserResponse;
import com.system.bank_system.Errors.IsEmailExists;
import com.system.bank_system.Mapper.UserMapper;
import com.system.bank_system.Models.User;
import com.system.bank_system.Repositories.UserRepo;
import com.system.bank_system.Services.User.UserService;
import com.system.bank_system.enums.UserRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse registerAdmin(RegisterUserRequest request) {
        Optional<User> adminFound = userRepo.findByEmail(request.getEmail());
        if (adminFound.isPresent()) {
            throw new IsEmailExists("email Already Exists");
        }
        User admin = userMapper.toAdminEntity(request);
        admin.setUserRole(UserRole.ROLE_ADMIN);
        admin.setPassword( passwordEncoder.encode(request.getPassword()) );

        return userMapper.toResponse(userRepo.save(admin));
    }
    
}

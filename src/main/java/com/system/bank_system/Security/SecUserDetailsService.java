package com.system.bank_system.Security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.system.bank_system.Errors.RecordNotFound;
import com.system.bank_system.Models.User;
import com.system.bank_system.Repositories.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SecUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isEmpty()) {
            throw new RecordNotFound("Email Not Found");
        }
        return new SecUserDetails(user.get());
    }
    
}

package com.system.bank_system.Utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.system.bank_system.Models.User;
import com.system.bank_system.Security.SecUserDetails;

@Component
public class SecurityUtils {
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        SecUserDetails secUserDetails =
                (SecUserDetails) authentication.getPrincipal();

        return secUserDetails.getUser();
    }
}

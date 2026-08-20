package com.system.bank_system.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final SecUserDetailsService secUserDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter; 

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf( csrf -> csrf.disable() )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider(secUserDetailsService, passwordEncoder()))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/api/v1/auth/logIn", "/api/v1/auth/registerAdmin","/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll()
                        .requestMatchers("/api/v1/auth/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/customer/getCustomerById", "/api/v1/customer/updateCustomer", "/api/v1/accounts/getAllAccounts").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers("/api/v1/accounts/**", "/api/v1/customer/**").hasAnyRole("ADMIN", "EMPLOYEE", "CUSTOMER")
                        .requestMatchers("/api/v1/auth/registerCustomer").hasAnyRole("EMPLOYEE", "ADMIN")
                        .requestMatchers("/api/v1/transaction/**").hasAnyRole("ADMIN", "EMPLOYEE", "CUSTOMER").anyRequest().authenticated()
                    );
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider( UserDetailsService secUserDetailsService, PasswordEncoder passwordEncoder ){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(secUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return configuration.getAuthenticationManager();
    }
}




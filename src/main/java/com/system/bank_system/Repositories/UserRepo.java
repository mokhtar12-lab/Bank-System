package com.system.bank_system.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.bank_system.Models.User;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
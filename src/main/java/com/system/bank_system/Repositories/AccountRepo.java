package com.system.bank_system.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.bank_system.Models.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
}

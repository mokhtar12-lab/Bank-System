package com.system.bank_system.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.bank_system.Models.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByNationalId(String nationalId);
}

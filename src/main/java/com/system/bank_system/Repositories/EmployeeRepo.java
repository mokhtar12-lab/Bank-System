package com.system.bank_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.bank_system.Models.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    
}

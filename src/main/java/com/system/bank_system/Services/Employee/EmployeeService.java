package com.system.bank_system.Services.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.bank_system.DTOs.Responses.EmployeeResponse;
public interface EmployeeService {

    // EmployeeResponse registerEmployee(RegisterEmployeeRequest request);

    EmployeeResponse getEmployeeById(Long id);

    // List<EmployeeResponse> getAllEmployees();

    Page<EmployeeResponse> getAllEmployees( Pageable pageable );

    // EmployeeResponse updateEmployee(Long id, UpdateEmployeeRequest request);

    void deleteEmployee(Long id);
}

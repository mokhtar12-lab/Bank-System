package com.system.bank_system.Services.Employee.Imp;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.system.bank_system.DTOs.Responses.EmployeeResponse;
import com.system.bank_system.Errors.RecordNotFound;
import com.system.bank_system.Mapper.EmployeeMapper;
import com.system.bank_system.Models.Employee;
import com.system.bank_system.Repositories.EmployeeRepo;
import com.system.bank_system.Services.Employee.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Optional<Employee> emp = employeeRepo.findById(id);
        if(emp.isPresent()){
            return employeeMapper.toResponse(emp.get());
        }
        throw new RecordNotFound("Employee Not Found");
    }

    @Override
    public Page<EmployeeResponse> getAllEmployees(Pageable pageable) {
        Page<Employee> emp = employeeRepo.findAll(pageable);
        if(emp.isEmpty()){
            throw new RecordNotFound("No Employees Found");
        }
        return emp.map(employeeMapper::toResponse);
    }
    

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> emp = employeeRepo.findById(id);
        if(!emp.isPresent()){
            throw new RecordNotFound("Employee Has Deleted or Not Found");
        }
        employeeRepo.deleteById(id);
    }


}
package com.system.bank_system.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.bank_system.DTOs.Responses.EmployeeResponse;
import com.system.bank_system.Services.Employee.Imp.EmployeeServiceImp;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeServiceImp employeeServiceImp;

    @GetMapping("/getEmployeeById")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@RequestParam Long id){
        return ResponseEntity.ok( employeeServiceImp.getEmployeeById(id) );
    }

    @GetMapping("/getAllEmployees")
    public Page<EmployeeResponse> getAllEmployees(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size,
                @RequestParam(defaultValue = "employeeId") String sortBy,
                @RequestParam(defaultValue = "asc") String direction )
    {
    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);

    return employeeServiceImp.getAllEmployees(pageable);
    }

    @DeleteMapping("/deleteEmployeeById")
    public ResponseEntity<?> deleteEmployeeById(@RequestParam Long id){
        employeeServiceImp.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
package com.system.bank_system.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.system.bank_system.DTOs.Requests.employees.EmployeeRequest;
import com.system.bank_system.DTOs.Responses.AuthenticationResponse;
import com.system.bank_system.DTOs.Responses.EmployeeResponse;
import com.system.bank_system.Models.Employee;

@Mapper(componentModel="spring", uses=UserMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    @Mapping(target="employeeId", ignore=true)
    @Mapping(target="user", ignore=true)
    Employee toEntity(EmployeeRequest request);

    EmployeeResponse toResponse(Employee employee);

    AuthenticationResponse toRegisterResponse(Employee employee);

    @Mapping(target = "userRole", source = "user.userRole")
    List<EmployeeResponse> toResponseArray(List<Employee> employees);
}

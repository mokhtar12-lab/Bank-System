package com.system.bank_system.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.bank_system.DTOs.Requests.customers.UpdateCustomerRequest;
import com.system.bank_system.DTOs.Responses.CustomerResponse;
import com.system.bank_system.Services.Customer.Imp.CustomerServiceImp;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerServiceImp customerServiceImp;

    @GetMapping("/getCustomerById")
    public ResponseEntity<CustomerResponse> getCustomerById(@RequestParam Long id){
        return ResponseEntity.ok( customerServiceImp.getCustomerById(id) );
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody UpdateCustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerServiceImp.updateCustomer(request));
    }

    @GetMapping("/getAllCustomers")
    public Page<CustomerResponse> getAllCustomers(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size,
                @RequestParam(defaultValue = "customerId") String sortBy,
                @RequestParam(defaultValue = "asc") String direction )
    {
    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);

    return customerServiceImp.getAllCustomers(pageable);
    }

    @DeleteMapping("/deleteCustomerById")
    public ResponseEntity<?> deleteCustomerById(@RequestParam Long id){
        customerServiceImp.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
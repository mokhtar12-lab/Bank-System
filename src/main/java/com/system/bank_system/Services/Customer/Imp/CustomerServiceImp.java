package com.system.bank_system.Services.Customer.Imp;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.system.bank_system.DTOs.Requests.customers.UpdateCustomerRequest;
import com.system.bank_system.DTOs.Responses.CustomerResponse;
import com.system.bank_system.Errors.CustomerHasAccount;
import com.system.bank_system.Errors.RecordNotFound;
import com.system.bank_system.Mapper.CustomerMapper;
import com.system.bank_system.Mapper.UserMapper;
import com.system.bank_system.Models.Customer;
import com.system.bank_system.Repositories.CustomerRepo;
import com.system.bank_system.Repositories.UserRepo;
import com.system.bank_system.Services.Customer.CustomerService;
import com.system.bank_system.Validator.Validation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImp implements CustomerService{
    private final UserRepo userRepo;
    private final CustomerRepo customerRepo;
    private final UserMapper userMapper;
    private final CustomerMapper customerMapper;
    private final Validation validation;

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Optional<Customer> user = customerRepo.findById(id);
        if(user.isPresent()){
            return customerMapper.toResponse(user.get());
        }
        throw new RecordNotFound("Customer Not Found") ;
    }

    @Override
    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {
        Page<Customer> customers = customerRepo.findAll(pageable);
        if(customers.isEmpty()){
            throw new RecordNotFound("No Customers Found");
        }
        return customers.map( customerMapper::toResponse );
    }

    @Override @Transactional
    public CustomerResponse updateCustomer(UpdateCustomerRequest request) {
        Customer customerFound = validation.customerFound(request.getCustomerId());

        customerMapper.updateCustomerResponse(request, customerFound);
        userMapper.updateUserResponse(request.getUser(), customerFound.getUser());

        Customer updateCustomer = customerRepo.save(customerFound);
        return customerMapper.toResponse(updateCustomer);
    }

    @Override @Transactional
    public void deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if(!customer.isPresent()){
            throw new RecordNotFound("Customer Has Deleted or Not Found");
        }
        else if (!customer.get().getAccounts().isEmpty()) {
            throw new CustomerHasAccount("Cannot Delete Customer Because Has Account");
        }
        userRepo.deleteById(customer.get().getUser().getUserId());
    }
}
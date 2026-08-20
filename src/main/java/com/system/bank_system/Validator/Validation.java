package com.system.bank_system.Validator;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.system.bank_system.Errors.BalanceNotEmpty;
import com.system.bank_system.Errors.IsEmailExists;
import com.system.bank_system.Errors.IsNationalIdExists;
import com.system.bank_system.Errors.NotCustomerFound;
import com.system.bank_system.Models.Customer;
import com.system.bank_system.Repositories.AccountRepo;
import com.system.bank_system.Repositories.CustomerRepo;
import com.system.bank_system.Repositories.UserRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class Validation {
    private final UserRepo userRepo;
    private final CustomerRepo customerRepo;
    private final AccountRepo accountRepo;

    public boolean EmailIsExists(String email){
        if (userRepo.findByEmail(email).isPresent()) {
            throw new IsEmailExists("Email Already Exists.");
        }
        return false;
    }

    public boolean NationalIdIsExists(String nationalId){
        if (customerRepo.findByNationalId(nationalId).isPresent()) {
            throw new IsNationalIdExists("NationalID Already Exists.");
        }
        return false;
    }

    public boolean AccountNumberIsExists(String accountNumber){
        if (accountRepo.findByAccountNumber(accountNumber).isPresent()) {
            throw new NotCustomerFound("Account Number Already Exists.");
        }
        return false;
    }

    public Customer customerFound(Long id){
        Optional<Customer> customerFound = customerRepo.findById(id);
        if(customerFound.isEmpty()){
            throw new NotCustomerFound("Customer not found Cannot Create an Account ");
        }
        return customerFound.get();
    }

    public boolean cannotDeleteAccountIfHasBalance(BigDecimal balance){
        if(balance.compareTo(BigDecimal.ZERO) > 0){
            throw new BalanceNotEmpty("Cannot Delete Account Because You have Money");
        }
        return false;
    }

    public boolean amountMustBeGreaterThanZero(BigDecimal request) {
        if(request.compareTo(BigDecimal.ZERO) <= 0 ){
            throw new BalanceNotEmpty("Cannot Complete The Operation Because Haven't Money Enough");
        }
        return true;
    }

}
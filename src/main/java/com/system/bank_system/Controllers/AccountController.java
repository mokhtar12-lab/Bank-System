package com.system.bank_system.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.bank_system.DTOs.Requests.Account.CreateAccountRequest;
import com.system.bank_system.DTOs.Requests.Account.UpdateAccountRequest;
import com.system.bank_system.DTOs.Responses.AccountResponse;
import com.system.bank_system.Services.Account.Imp.AccountServiceIpm;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountServiceIpm accountServiceIpm;

    @PostMapping("/register")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountServiceIpm.createAccount(request));
    }

    @GetMapping("/getAccountById")
    public ResponseEntity<AccountResponse> getCustomerById(@RequestParam Long id){
        return ResponseEntity.ok( accountServiceIpm.getAccountById(id) );
    }

    @PutMapping("/updateAccount")
    public ResponseEntity<AccountResponse> updateCustomer(@RequestBody UpdateAccountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountServiceIpm.updateAccount(request));
    }

    @GetMapping("/getAllAccounts")
    public Page<AccountResponse> getAllAccountCustomers(
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(defaultValue = "accountId") String sortBy,
                        @RequestParam(defaultValue = "asc") String direction 
                    ){
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return accountServiceIpm.getAllAccounts(pageable);
    }

    @DeleteMapping("/deleteAccountById")
    public ResponseEntity<?> deleteCustomerById(@RequestParam Long id){
        accountServiceIpm.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
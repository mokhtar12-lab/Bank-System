package com.system.bank_system.Services.Account.Imp;

import java.util.Optional;

import com.system.bank_system.Errors.AccessDeniedException;
import com.system.bank_system.Models.User;
import com.system.bank_system.Utils.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.system.bank_system.DTOs.Requests.Account.CreateAccountRequest;
import com.system.bank_system.DTOs.Requests.Account.UpdateAccountRequest;
import com.system.bank_system.DTOs.Responses.AccountResponse;
import com.system.bank_system.Errors.RecordNotFound;
import com.system.bank_system.Mapper.AccountMapper;
import com.system.bank_system.Models.Account;
import com.system.bank_system.Models.Customer;
import com.system.bank_system.Repositories.AccountRepo;
import com.system.bank_system.Services.Account.AccountService;
import com.system.bank_system.Validator.Validation;
import com.system.bank_system.enums.AccountStatus;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AccountServiceIpm implements AccountService {
    private final AccountRepo accountRepo;
    private final Validation validation;
    private final AccountMapper accountMapper;
    private final SecurityUtils securityUtils;

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {
        Customer customer = validation.customerFound(request.getCustomer_id());
        validation.AccountNumberIsExists(request.getAccountNumber());
        Account accountEntity = accountMapper.toEntity(request);
        accountEntity.setCustomer(customer);
        accountEntity.setAccountStatus(AccountStatus.ACTIVE);
        Account saveAccount = accountRepo.save(accountEntity);
        return accountMapper.toResponse(saveAccount);
    }

    @Override
    public AccountResponse getAccountById(Long accountId) {
        Optional<Account> accountFound = accountRepo.findById(accountId);
        if(accountFound.isEmpty()){
            throw new RecordNotFound("Account not found");
        }
        User currentUser = securityUtils.getCurrentUser();
        if( !accountFound.get().getCustomer().getUser().getUserId().equals(currentUser.getUserId()) ){
            throw new AccessDeniedException("You Are Not Allowed to access this Account");
        }
        return accountMapper.toResponse(accountFound.get());
    }

    @Override
    public Page<AccountResponse> getAllAccounts(Pageable pageable) {
        Page<Account> accounts = accountRepo.findAll(pageable);
        if(accounts.isEmpty()){
            throw new RecordNotFound("No Account Found");
        }
        return accounts.map( accountMapper::toResponse );
    }


    @Override
    public AccountResponse updateAccount(UpdateAccountRequest request) {
        Optional<Account> accountFound = accountRepo.findById(request.getAccountId());
        if(accountFound.isEmpty()){
            throw new RecordNotFound("Account not found or has been deleted");
        }
        accountFound.get().setAccountStatus(request.getAccountStatus());
        accountFound.get().setAccountType(request.getAccountType());
        Account updatedAccount = accountRepo.save(accountFound.get());
        return accountMapper.toResponse(updatedAccount);
    }

    @Override
    public void deleteAccount(Long accountId) {
        Optional<Account> accountFound = accountRepo.findById(accountId);
        if(accountFound.isEmpty()){
            throw new RecordNotFound("Account not found or has been deleted");
        }
        validation.cannotDeleteAccountIfHasBalance(accountFound.get().getBalance());
        accountRepo.deleteById(accountId);
    }

}

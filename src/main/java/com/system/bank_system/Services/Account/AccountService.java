package com.system.bank_system.Services.Account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.bank_system.DTOs.Requests.Account.CreateAccountRequest;
import com.system.bank_system.DTOs.Requests.Account.UpdateAccountRequest;
import com.system.bank_system.DTOs.Responses.AccountResponse;

public interface AccountService {
    public AccountResponse createAccount(CreateAccountRequest account);

    public AccountResponse getAccountById(Long accountId);

    Page<AccountResponse> getAllAccounts( Pageable pageable );

    public AccountResponse updateAccount(UpdateAccountRequest request);

    public void deleteAccount(Long accountId);
}

package com.system.bank_system.Services.Transaction.Imp;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.system.bank_system.DTOs.Requests.Transactions.DepositRequest;
import com.system.bank_system.DTOs.Requests.Transactions.TransferRequest;
import com.system.bank_system.DTOs.Requests.Transactions.WithdrawRequest;
import com.system.bank_system.DTOs.Responses.TransactionResponse;
import com.system.bank_system.Errors.AccessDeniedException;
import com.system.bank_system.Errors.AccountStatusFailed;
import com.system.bank_system.Errors.BalanceException;
import com.system.bank_system.Errors.RecordNotFound;
import com.system.bank_system.Errors.theSameAccount;
import com.system.bank_system.Mapper.TransactionMapper;
import com.system.bank_system.Models.Account;
import com.system.bank_system.Models.Customer;
import com.system.bank_system.Models.Transaction;
import com.system.bank_system.Models.User;
import com.system.bank_system.Repositories.AccountRepo;
import com.system.bank_system.Repositories.TransactionRepo;
import com.system.bank_system.Services.Transaction.TransactionService;
import com.system.bank_system.Utils.SecurityUtils;
import com.system.bank_system.Validator.Validation;
import com.system.bank_system.enums.AccountStatus;
import com.system.bank_system.enums.TransactionStatus;
import com.system.bank_system.enums.TransactionType;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceIpm implements TransactionService {
    private final TransactionRepo transactionRepo;
    private final TransactionMapper transactionMapper;
    private final AccountRepo accountRepo;
    private final Validation validation;
    private final SecurityUtils securityUtils;

    // Extra Methods
    public Account accountFound(Long id) {
        Optional<Account> account = accountRepo.findById(id);
        if (account.isEmpty()) {
            throw new RecordNotFound("Account Not Found");
        }
        return account.get();
    }

    private void checkAccountOwner(Account account) {

        User currentUser = securityUtils.getCurrentUser();

        if (!account.getCustomer()
                .getUser()
                .getUserId()
                .equals(currentUser.getUserId())) {

            throw new AccessDeniedException("Access Denied");
        }
    }

    // -----------------------------
    @Override
    @Transactional
    public TransactionResponse deposit(DepositRequest request) {
        Transaction transaction = transactionMapper.toDeposit(request);
        Account findAccount = accountFound(request.getAccountId());
        checkAccountOwner(findAccount);
        if (findAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new AccountStatusFailed("Account Not Active Cannot Complete The Operation");
        }
        validation.amountMustBeGreaterThanZero(request.getAmount());
        findAccount.setBalance(findAccount.getBalance().add(request.getAmount()));
        accountRepo.save(findAccount);

        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setFromAccount(findAccount);

        Transaction saveTransaction = transactionRepo.save(transaction);
        return transactionMapper.toResponse(saveTransaction);
    }

    @Override
    @Transactional
    public TransactionResponse withdraw(WithdrawRequest request) {
        Transaction transaction = transactionMapper.toWithdraw(request);
        Account findAccount = accountFound(request.getAccountId());
        checkAccountOwner(findAccount);
        if (findAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new AccountStatusFailed("Account Not Active Cannot Complete The Operation");
        }
        validation.amountMustBeGreaterThanZero(request.getAmount());
        if (findAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new BalanceException("Insufficient Balance");
        }
        findAccount.setBalance(findAccount.getBalance().subtract(request.getAmount()));
        accountRepo.save(findAccount);
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setFromAccount(findAccount);

        Transaction saveTransaction = transactionRepo.save(transaction);
        return transactionMapper.toResponse(saveTransaction);
    }

    @Override
    @Transactional
    public TransactionResponse transfer(TransferRequest request) {
        Transaction saveTransaction = transactionMapper.toTransfer(request);
        Account fromAccount = accountFound(request.getFromAccount());
        checkAccountOwner(fromAccount);
        Account toAccount = accountFound(request.getToAccount());
        if (fromAccount.getAccountStatus() != AccountStatus.ACTIVE
                || toAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new AccountStatusFailed("Account Not Active Cannot Complete The Operation");
        }
        validation.amountMustBeGreaterThanZero(request.getAmount());
        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new BalanceException("Insufficient Balance");
        }
        if (fromAccount.getAccountNumber().compareTo(toAccount.getAccountNumber()) == 0) {
            throw new theSameAccount("Cannot Transfer To the Same Account");
        }
        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));
        accountRepo.save(fromAccount);
        accountRepo.save(toAccount);
        saveTransaction.setTransactionStatus(TransactionStatus.SUCCESS);
        saveTransaction.setTransactionType(TransactionType.TRANSFER);
        saveTransaction.setFromAccount(fromAccount);
        saveTransaction.setToAccount(toAccount);
        return transactionMapper.toResponse(transactionRepo.save(saveTransaction));
    }

    @Override
    public TransactionResponse getTransactionById(Long transactionId) {
        Optional<Transaction> findTransaction = transactionRepo.findById(transactionId);
        if (findTransaction.isEmpty()) {
            throw new RecordNotFound("Transaction Not Found");
        }
        User currentUser = securityUtils.getCurrentUser();
        if(!findTransaction.get().getFromAccount().getCustomer().getUser().getUserId().equals(currentUser.getUserId())){
            throw new AccessDeniedException("Cannot Access Transactions another User");
        }
        return transactionMapper.toResponse(findTransaction.get());
    }

    @Override
    public Page<TransactionResponse> getAllTransactions(Pageable pageable) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<Transaction> transactions = transactionRepo.findAllUsersTransactions(currentEmail, pageable);
        return transactions.map(transactionMapper::toResponse);
    }

    @Override
    public Page<TransactionResponse> getAllTransfers(Pageable pageable) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<Transaction> transactions = transactionRepo.findAllUsersTransfer(currentEmail, pageable);
        return transactions.map(transactionMapper::toResponse);
    }

    @Override
    public Page<TransactionResponse> getAccountTransactions(Pageable pageable, Long accountId) {
        User currentUser = securityUtils.getCurrentUser();
        Customer customer = currentUser.getCustomer();
        Account account = accountFound(accountId);
        if (!account.getCustomer().getCustomerId().equals(customer.getCustomerId())) {
            throw new AccessDeniedException("You cannot access another customer's transactions");
        }
        Page<Transaction> transactions = transactionRepo
                .findByFromAccountAccountIdOrToAccountAccountId(account.getAccountId(), account.getAccountId(),
                        pageable);
        return transactions.map(transactionMapper::toResponse);
    }

    @Override
    public Page<TransactionResponse> getByTransactionType(Pageable pageable, TransactionType type) {
        Page<Transaction> findType = transactionRepo.findByTransactionType(type, pageable);
        User currentUser = securityUtils.getCurrentUser();
        if (findType.getContent().stream()
                .anyMatch(transaction -> !transaction.getFromAccount()
                        .getCustomer()
                        .getUser()
                        .getUserId()
                        .equals(currentUser.getUserId()))) {
            throw new AccessDeniedException("Access Denied");
        }
        if (findType.isEmpty()) {
            throw new RecordNotFound("Not There Transaction >> " + type);
        }
        return findType.map(transactionMapper::toResponse);
    }

}
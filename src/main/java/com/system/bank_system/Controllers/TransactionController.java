package com.system.bank_system.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.bank_system.DTOs.Requests.Transactions.DepositRequest;
import com.system.bank_system.DTOs.Requests.Transactions.TransferRequest;
import com.system.bank_system.DTOs.Requests.Transactions.WithdrawRequest;
import com.system.bank_system.DTOs.Responses.TransactionResponse;
import com.system.bank_system.Services.Transaction.Imp.TransactionServiceIpm;
import com.system.bank_system.enums.TransactionType;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionServiceIpm transactionServiceIpm;

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse> deposit(@RequestBody DepositRequest request){
        return ResponseEntity.ok(transactionServiceIpm.deposit(request));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponse> withdraw(@RequestBody WithdrawRequest request){
        return ResponseEntity.ok(transactionServiceIpm.withdraw(request));
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transfer(@RequestBody TransferRequest request){
        return ResponseEntity.ok(transactionServiceIpm.transfer(request));
    }

    @GetMapping("/getOneTransaction")
    public ResponseEntity<TransactionResponse> getTransactionById(@RequestParam Long id){
        return ResponseEntity.ok( transactionServiceIpm.getTransactionById(id) ) ;
    }


    @GetMapping("/getAllTransactions")
    public Page<TransactionResponse> getAllTransactions(
                @PageableDefault(
                        page = 0,
                        size = 10,
                        sort = "transactionId",
                        direction = Sort.Direction.ASC
                )
                Pageable pageable
            ){
        return transactionServiceIpm.getAllTransactions(pageable);
    }

    @GetMapping("/getAllTransfers")
    public Page<TransactionResponse> getAllTransfers(
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = "transactionId",
                    direction = Sort.Direction.ASC
            )
            Pageable pageable
    ){
        return transactionServiceIpm.getAllTransfers(pageable);
    }

    @GetMapping("/getAccountTransactions")
    public Page<TransactionResponse> getAccountTransactions(@RequestParam Long accountId,
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "10") int size,
                    @RequestParam(defaultValue = "transactionId") String sortBy,
                    @RequestParam(defaultValue = "asc") String direction
            ){
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return transactionServiceIpm.getAccountTransactions(pageable, accountId);
    }

    @GetMapping("/getByTransactionType")
    public Page<TransactionResponse> getByTransactionType(@RequestParam TransactionType type,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(defaultValue = "transactionId") String sortBy,
                        @RequestParam(defaultValue = "asc") String direction
            ){
            Sort sort = direction.equalsIgnoreCase("desc")
                    ? Sort.by(sortBy).descending()
                    : Sort.by(sortBy).ascending();

            Pageable pageable = PageRequest.of(page, size, sort);
        return transactionServiceIpm.getByTransactionType(pageable, type);
    }
}
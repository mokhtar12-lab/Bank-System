package com.system.bank_system.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.system.bank_system.Models.Transaction;
import com.system.bank_system.enums.TransactionType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByFromAccountAccountIdOrToAccountAccountId(Long fromAccountId, Long toAccountId, Pageable pageable);
    Page<Transaction> findByTransactionType(TransactionType type, Pageable pageable);

    @Query(value = """ 
                SELECT t FROM Transaction t WHERE t.fromAccount.customer.user.email = :email
                ORDER BY t.createdDate ASC
                """)
    Page<Transaction> findAllUsersTransactions(@Param("email") String email, Pageable pageable);


    @Query(value = """ 
                SELECT t FROM Transaction t WHERE t.fromAccount.customer.user.email = :email 
                OR t.toAccount.customer.user.email = :email 
                ORDER BY t.createdDate ASC
                """)
    Page<Transaction> findAllUsersTransfer(@Param("email") String email, Pageable pageable);
}
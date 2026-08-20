package com.system.bank_system.Models;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.system.bank_system.enums.TransactionStatus;
import com.system.bank_system.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="transactions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction extends BaseAuditableEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long transactionId;

    @NotNull
    private BigDecimal amount;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    private String description;
    
    private LocalDate transactionDate;

    @ManyToOne
    private Account fromAccount;

    @ManyToOne
    private Account toAccount;
}

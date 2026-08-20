package com.system.bank_system.Models;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customers")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long customerId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull @Column(unique=true, nullable=false)
    private String nationalId;
    private LocalDate dateOfBirth;
    @NotNull
    private String address;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", unique=true, nullable=false)
    private User user;

    @OneToMany(mappedBy="customer", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Account> accounts;

    public void addAccount( Account account ){
        accounts.add(account);
        account.setCustomer(this);
    }
}
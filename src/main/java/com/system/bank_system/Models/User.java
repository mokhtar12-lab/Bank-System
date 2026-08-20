package com.system.bank_system.Models;

import com.system.bank_system.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseAuditableEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;
    @NotNull
    private String userName;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private UserRole userRole;

    private boolean enabled;

    @OneToOne(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
    private Customer customer ;

    @OneToOne(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
    private Employee employee ;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        if (customer != null) {
            customer.setUser(this);
        }
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        if (employee != null) {
            employee.setUser(this);
        }
    }
}
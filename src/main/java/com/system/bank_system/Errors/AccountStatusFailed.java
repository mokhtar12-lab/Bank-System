package com.system.bank_system.Errors;

public class AccountStatusFailed extends RuntimeException {
    public AccountStatusFailed(){};
    public AccountStatusFailed(String message) {
        super(message);
    }
}
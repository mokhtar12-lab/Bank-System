package com.system.bank_system.Errors;

public class BalanceException extends RuntimeException {
    public BalanceException(){};
    public BalanceException(String message){
        super(message);
    }
}

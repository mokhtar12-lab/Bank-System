package com.system.bank_system.Errors;

public class BalanceNotEmpty extends RuntimeException {
    public BalanceNotEmpty(){};

    public BalanceNotEmpty(String message){
        super(message);
    }
}

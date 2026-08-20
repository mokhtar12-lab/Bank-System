package com.system.bank_system.Errors;

public class CustomerHasAccount extends RuntimeException{
    public CustomerHasAccount(){};
    public CustomerHasAccount(String message){
        super(message);
    };
}

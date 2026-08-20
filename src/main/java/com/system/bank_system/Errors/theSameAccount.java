package com.system.bank_system.Errors;

public class theSameAccount extends RuntimeException {
    public theSameAccount(){};
    public theSameAccount(String message){
        super(message);
    };
}

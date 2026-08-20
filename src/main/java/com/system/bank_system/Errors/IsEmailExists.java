package com.system.bank_system.Errors;

public class IsEmailExists extends RuntimeException {
    public IsEmailExists(){}

    public IsEmailExists(String message){
        super(message);
    }
}

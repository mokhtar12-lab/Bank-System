package com.system.bank_system.Errors;

public class AccessDeniedException extends  RuntimeException {
    public AccessDeniedException(){};
    public AccessDeniedException(String message){
        super(message);
    };
}

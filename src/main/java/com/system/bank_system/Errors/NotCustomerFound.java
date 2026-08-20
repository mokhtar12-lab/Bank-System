package com.system.bank_system.Errors;

public class NotCustomerFound extends RuntimeException {
    public NotCustomerFound(){}
    public NotCustomerFound(String message) {
        super(message);
    }
}

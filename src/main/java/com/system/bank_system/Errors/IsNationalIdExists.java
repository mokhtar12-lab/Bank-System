package com.system.bank_system.Errors;

public class IsNationalIdExists extends RuntimeException {
    public IsNationalIdExists() {
    }

    public IsNationalIdExists(String message) {
        super(message);
    }
}

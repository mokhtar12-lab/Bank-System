package com.system.bank_system.Errors;

public class RecordNotFound extends RuntimeException{
    public RecordNotFound(){}
    public RecordNotFound(String message){
        super(message);
    }
}

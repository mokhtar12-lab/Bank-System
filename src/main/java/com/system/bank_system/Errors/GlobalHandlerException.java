package com.system.bank_system.Errors;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler{

    @ExceptionHandler(RecordNotFound.class)
    public ResponseEntity<?> handleRecordNotFoundException(RecordNotFound ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(IsEmailExists.class)
    public ResponseEntity<?> handleRecordNotFoundException(IsEmailExists ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(NotCustomerFound.class)
    public ResponseEntity<?> handleIllegalArgumentExceptionHandling(NotCustomerFound ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IsNationalIdExists.class)
    public ResponseEntity<?> handleIllegalArgumentExceptionHandling(IsNationalIdExists ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(BalanceNotEmpty.class)
    public ResponseEntity<?> handleBalanceNotEmptyExceptionHandling(BalanceNotEmpty ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(CustomerHasAccount.class)
    public ResponseEntity<?> handleCustomerHasAccountExceptionHandling(CustomerHasAccount ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(AccountStatusFailed.class)
    public ResponseEntity<?> handleAccountStatusFailedExceptionHandling(AccountStatusFailed ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(BalanceException.class)
    public ResponseEntity<?> handleBalanceExceptionHandling(BalanceException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(theSameAccount.class)
    public ResponseEntity<?> handleTheSameAccountExceptionHandling(theSameAccount ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedExceptionHandling(AccessDeniedException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


}
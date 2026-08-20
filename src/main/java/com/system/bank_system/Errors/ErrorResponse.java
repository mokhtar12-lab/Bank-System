package com.system.bank_system.Errors;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    private String exception;
    private Boolean success;
    private List<String> errors;
    private LocalDateTime locale;


    public ErrorResponse(String exception, List<String> errors) {
        this.exception = exception;
        this.success = Boolean.FALSE;
        this.locale = LocalDateTime.now();
        this.errors = errors;
    }
    public String getException() {
        return exception;
    }
    public void setException(String exception) {
        this.exception = exception;
    }

    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public LocalDateTime getLocale() {
        return locale;
    }
    public void setLocale(LocalDateTime locale) {
        this.locale = locale;
    }

    public List<String> getErrors() {
        return errors;
    }
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}

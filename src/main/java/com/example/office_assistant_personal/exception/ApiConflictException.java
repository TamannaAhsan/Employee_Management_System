package com.example.office_assistant_personal.exception;

import org.springframework.http.HttpStatus;

public class ApiConflictException extends ApiException {

    public ApiConflictException() {
    }

    /**
     * @param message
     */
    public ApiConflictException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ApiConflictException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ApiConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getServiceStatus() {
        return HttpStatus.CONFLICT;
    }
}

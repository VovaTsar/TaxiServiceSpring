package com.mytaxi.model.customExceptions;

public class DataValidateRuntimeException extends RuntimeException {

    public DataValidateRuntimeException() {
    }

    public DataValidateRuntimeException(String message) {
        super(message);
    }

    public DataValidateRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}

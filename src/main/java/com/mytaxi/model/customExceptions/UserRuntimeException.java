package com.mytaxi.model.customExceptions;

public class UserRuntimeException extends RuntimeException {
    public UserRuntimeException() {
    }

    public UserRuntimeException(String message) {
        super(message);
    }

    public UserRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}

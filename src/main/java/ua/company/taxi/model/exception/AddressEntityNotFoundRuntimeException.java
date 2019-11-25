package ua.company.taxi.model.exception;

public class AddressEntityNotFoundRuntimeException extends RuntimeException {

    public AddressEntityNotFoundRuntimeException() {
    }

    public AddressEntityNotFoundRuntimeException(String message) {
        super(message);
    }

    public AddressEntityNotFoundRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}

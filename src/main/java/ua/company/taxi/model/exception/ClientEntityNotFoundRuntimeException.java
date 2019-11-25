package ua.company.taxi.model.exception;

public class ClientEntityNotFoundRuntimeException extends RuntimeException {

    public ClientEntityNotFoundRuntimeException() {
    }

    public ClientEntityNotFoundRuntimeException(String message) {
        super(message);
    }

    public ClientEntityNotFoundRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}

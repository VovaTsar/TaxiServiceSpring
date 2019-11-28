package ua.company.taxi.model.exception;

public class CarEntityNotFoundRuntimeException extends RuntimeException {
    public CarEntityNotFoundRuntimeException() { }

    public CarEntityNotFoundRuntimeException(String message) {
        super(message);
    }

    public CarEntityNotFoundRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}

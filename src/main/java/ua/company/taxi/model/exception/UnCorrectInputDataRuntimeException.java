package ua.company.taxi.model.exception;

public class UnCorrectInputDataRuntimeException extends RuntimeException {
    public UnCorrectInputDataRuntimeException() { }

    public UnCorrectInputDataRuntimeException(String message) {
        super(message);
    }

    public UnCorrectInputDataRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}

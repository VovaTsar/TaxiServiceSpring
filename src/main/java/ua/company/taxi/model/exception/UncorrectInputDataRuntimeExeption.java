package ua.company.taxi.model.exception;

public class UncorrectInputDataRuntimeExeption extends RuntimeException {
    public UncorrectInputDataRuntimeExeption() {
    }

    public UncorrectInputDataRuntimeExeption(String message) {
        super(message);
    }

    public UncorrectInputDataRuntimeExeption(String message, Throwable cause) {
        super(message, cause);
    }
}

package ua.company.taxi.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.company.taxi.model.exception.AddressEntityNotFoundRuntimeException;
import ua.company.taxi.model.exception.CarEntityNotFoundRuntimeException;
import ua.company.taxi.model.exception.UnCorrectInputDataRuntimeException;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler({SQLException.class,
            NullPointerException.class,
            AddressEntityNotFoundRuntimeException.class,
            CarEntityNotFoundRuntimeException.class,
            UnCorrectInputDataRuntimeException.class,
            IllegalArgumentException.class,
            Throwable.class})
    public String handleException() {
        return "errorSpring";
    }

}

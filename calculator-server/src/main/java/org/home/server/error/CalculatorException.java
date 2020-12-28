package org.home.server.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CalculatorException extends RuntimeException {
    public CalculatorException() {
        super();
    }

    public CalculatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculatorException(String message) {
        super(message);
    }

    public CalculatorException(Throwable cause) {
        super(cause);
    }
}
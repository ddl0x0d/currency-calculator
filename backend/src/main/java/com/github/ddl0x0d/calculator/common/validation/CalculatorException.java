package com.github.ddl0x0d.calculator.common.validation;

import lombok.Getter;

@Getter
public class CalculatorException extends RuntimeException {

    private final String errorCode;
    private final Object[] arguments;

    public CalculatorException(String errorCode, Object... arguments) {
        this.errorCode = errorCode;
        this.arguments = arguments;
    }
}

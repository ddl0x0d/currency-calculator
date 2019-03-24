package com.github.ddl0x0d.calculator.currency.provider;

import com.github.ddl0x0d.calculator.common.validation.CalculatorException;

import static com.github.ddl0x0d.calculator.common.validation.ErrorCodes.CURRENCY_PROVIDER;

public class CurrencyProviderException extends CalculatorException {

    public CurrencyProviderException(String message) {
        super(CURRENCY_PROVIDER, message);
    }
}

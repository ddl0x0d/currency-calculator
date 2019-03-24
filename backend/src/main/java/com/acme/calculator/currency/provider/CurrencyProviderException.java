package com.acme.calculator.currency.provider;

import com.acme.calculator.common.validation.CalculatorException;

import static com.acme.calculator.common.validation.ErrorCodes.CURRENCY_PROVIDER;

public class CurrencyProviderException extends CalculatorException {

    public CurrencyProviderException(String message) {
        super(CURRENCY_PROVIDER, message);
    }
}

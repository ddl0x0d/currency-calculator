package com.github.ddl0x0d.calculator.currency.provider;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ONE;
import static java.util.Arrays.asList;

@Component
public class CurrencyProviderMock implements CurrencyProvider {

    static final List<String> CURRENCIES = asList("EUR", "GBP", "RUB", "USD");

    @Override
    public List<String> getCurrencies() {
        return CURRENCIES;
    }

    @Override
    public BigDecimal getRate(String from, String to) {
        return ONE;
    }
}

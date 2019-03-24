package com.github.ddl0x0d.calculator.test;

import com.github.ddl0x0d.calculator.currency.CurrencyService;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;

public interface CurrencyTestSupport {

    default void expectCurrencies(String... currencies) {
        when(currencyService().getCurrencies()).thenReturn(asList(currencies));
    }

    CurrencyService currencyService();

}

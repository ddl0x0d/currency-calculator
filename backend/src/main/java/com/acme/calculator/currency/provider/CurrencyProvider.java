package com.acme.calculator.currency.provider;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyProvider {

    List<String> getCurrencies();

    BigDecimal getRate(String from, String to);

}

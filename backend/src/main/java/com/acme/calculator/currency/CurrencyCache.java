package com.acme.calculator.currency;

import com.acme.calculator.currency.provider.CurrencyProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
class CurrencyCache {

    static final String CURRENCIES = "currencies";
    static final String RATES = "rates";

    private final CurrencyProvider provider;

    @Cacheable(cacheNames = CURRENCIES, key = "'currencies'", unless = "#result.isEmpty()")
    public List<String> getCurrencies() {
        return provider.getCurrencies();
    }

    @Cacheable(RATES)
    public BigDecimal getRate(String from, String to) {
        return provider.getRate(from, to);
    }
}

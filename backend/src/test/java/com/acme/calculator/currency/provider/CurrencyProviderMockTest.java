package com.acme.calculator.currency.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static com.acme.calculator.currency.provider.CurrencyProviderMock.CURRENCIES;
import static com.acme.calculator.test.Constants.FROM;
import static com.acme.calculator.test.Constants.TO;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@Import({CurrencyProviderMock.class, CurrencyProviderFixer.class})
public class CurrencyProviderMockTest {

    @Autowired
    private CurrencyProvider provider;

    @Test
    public void getCurrencies() {
        List<String> result = provider.getCurrencies();

        assertThat(result).containsExactlyElementsOf(CURRENCIES);
    }

    @Test
    public void convert() {
        BigDecimal result = provider.getRate(FROM, TO);

        assertThat(result).isOne();
    }
}

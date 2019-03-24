package com.github.ddl0x0d.calculator.currency.provider;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.github.ddl0x0d.calculator.currency.provider.CurrencyProviderMock.CURRENCIES;
import static com.github.ddl0x0d.calculator.test.Constants.FROM;
import static com.github.ddl0x0d.calculator.test.Constants.TO;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
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

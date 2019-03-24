package com.acme.calculator.currency;

import com.acme.calculator.currency.provider.CurrencyProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static com.acme.calculator.test.Constants.CURRENCIES;
import static com.acme.calculator.test.Constants.FROM;
import static com.acme.calculator.test.Constants.RATE;
import static com.acme.calculator.test.Constants.TO;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Import({CurrencyCache.class, CurrencyConfig.class})
abstract class AbstractCurrencyCacheTest {

    @Autowired private CurrencyCache cache;
    @Autowired private CacheManager cacheManager;

    @MockBean
    private CurrencyProvider provider;

    @Test
    public void getCurrencies_missing() {
        expectCurrencies();

        assertThat(cache.getCurrencies()).isEmpty();
        assertThat(cache.getCurrencies()).isEmpty();
        assertThat(cache.getCurrencies()).isEmpty();

        verify(provider, times(3)).getCurrencies();
        invalidateCache(CurrencyCache.CURRENCIES);
    }

    @Test
    public void getCurrencies_present() {
        expectCurrencies(CURRENCIES);

        assertThat(cache.getCurrencies()).containsExactly(CURRENCIES);
        assertThat(cache.getCurrencies()).containsExactly(CURRENCIES);
        assertThat(cache.getCurrencies()).containsExactly(CURRENCIES);

        verify(provider, atMost(1)).getCurrencies();
        invalidateCache(CurrencyCache.CURRENCIES);
    }

    @Test
    public void getRate() {
        expectRate(FROM, TO, RATE);

        assertThat(cache.getRate(FROM, TO)).isEqualTo(RATE);
        assertThat(cache.getRate(FROM, TO)).isEqualTo(RATE);
        assertThat(cache.getRate(FROM, TO)).isEqualTo(RATE);

        verify(provider, atMost(1)).getRate(FROM, TO);
        invalidateCache(CurrencyCache.RATES);
    }

    private void expectCurrencies(String... currencies) {
        when(provider.getCurrencies()).thenReturn(asList(currencies));
    }

    private void expectRate(String from, String to, BigDecimal rate) {
        when(provider.getRate(from, to)).thenReturn(rate);
    }

    private void invalidateCache(String name) {
        cacheManager.getCache(name).clear();
    }
}

package com.acme.calculator.common.validation;

import org.junit.Test;

import static com.acme.calculator.test.Constants.EUR;
import static com.acme.calculator.test.validation.ValidationMessages.currencyInvalid;
import static com.acme.calculator.test.validation.ValidationMessages.currencyUnsupported;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class CurrencyValidatorTest extends AbstractConvertibleValidatorTest {

    @Test
    public void validate_invalid_currencyInvalid_empty() {
        String currency = "";

        assertThatViolationsFor(new TestCurrencyBean(currency))
            .contains(tuple("currency", currencyInvalid(currency)));

        verifyZeroInteractions(currencyService());
    }

    @Test
    public void validate_invalid_currencyInvalid_length() {
        String currency = "euro";

        assertThatViolationsFor(new TestCurrencyBean(currency))
            .contains(tuple("currency", currencyInvalid(currency)));

        verifyZeroInteractions(currencyService());
    }

    @Test
    public void validate_invalid_currencyInvalid_case() {
        String currency = "eur";

        assertThatViolationsFor(new TestCurrencyBean(currency))
            .contains(tuple("currency", currencyInvalid(currency)));

        verifyZeroInteractions(currencyService());
    }

    @Test
    public void validate_invalid_currencyUnsupported() {
        String currency = "ABC";
        expectCurrencies(EUR);

        assertThatViolationsFor(new TestCurrencyBean(currency))
            .containsExactly(tuple("currency", currencyUnsupported(currency)));

        verify(currencyService()).getCurrencies();
    }

    @Test
    public void validate_valid_null() {
        String currency = null;

        assertThatViolationsFor(new TestCurrencyBean(currency)).isEmpty();

        verifyZeroInteractions(currencyService());
    }

    @Test
    public void validate_valid_happyPath() {
        String currency = EUR;
        expectCurrencies(currency);

        assertThatViolationsFor(new TestCurrencyBean(currency)).isEmpty();

        verify(currencyService()).getCurrencies();
    }

    private static class TestCurrencyBean {

        @Currency
        @SuppressWarnings({"FieldCanBeLocal", "unused"})
        private final String currency;

        TestCurrencyBean(String currency) {
            this.currency = currency;
        }
    }
}

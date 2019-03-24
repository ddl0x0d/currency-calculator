package com.acme.calculator.common.validation;

import org.junit.Before;
import org.junit.Test;

import static com.acme.calculator.test.Constants.CURRENCIES;
import static com.acme.calculator.test.Constants.EUR;
import static com.acme.calculator.test.Constants.FROM;
import static com.acme.calculator.test.Constants.TO;
import static com.acme.calculator.test.validation.ValidationMessages.currencySame;
import static org.assertj.core.groups.Tuple.tuple;

public class DifferentCurrenciesValidatorTest extends AbstractConvertibleValidatorTest {

    @Before
    public void setUp() {
        expectCurrencies(CURRENCIES);
    }

    @Test
    public void validate_invalid_currencySame() {
        TestConvertible convertible = new TestConvertible(EUR, EUR);

        assertThatViolationsFor(convertible, ConvertibleChecks.class)
            .containsExactly(tuple("", currencySame()));
    }

    @Test
    public void validate_valid_currencies_null_from() {
        TestConvertible convertible = new TestConvertible(null, TO);

        assertThatViolationsFor(convertible, ConvertibleChecks.class).isEmpty();
    }

    @Test
    public void validate_valid_currencies_null_to() {
        TestConvertible convertible = new TestConvertible(FROM, null);

        assertThatViolationsFor(convertible, ConvertibleChecks.class).isEmpty();
    }

    @Test
    public void validate_valid_currencies_null_both() {
        TestConvertible convertible = new TestConvertible(null, null);

        assertThatViolationsFor(convertible, ConvertibleChecks.class).isEmpty();
    }

    @Test
    public void validate_valid_currencies_different() {
        TestConvertible convertible = new TestConvertible(FROM, TO);

        assertThatViolationsFor(convertible, ConvertibleChecks.class).isEmpty();
    }
}

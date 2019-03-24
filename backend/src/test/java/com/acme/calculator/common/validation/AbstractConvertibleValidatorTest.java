package com.acme.calculator.common.validation;

import com.acme.calculator.common.dto.Convertible;
import com.acme.calculator.currency.CurrencyService;
import com.acme.calculator.test.CurrencyTestSupport;
import com.acme.calculator.test.validation.ValidationTestSupport;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validator;

@RunWith(SpringRunner.class)
@ImportAutoConfiguration(ValidationAutoConfiguration.class)
abstract class AbstractConvertibleValidatorTest
    implements ValidationTestSupport, CurrencyTestSupport {

    @Autowired
    private Validator validator;

    @MockBean
    private CurrencyService service;

    @Override
    public Validator validator() {
        return validator;
    }

    @Override
    public CurrencyService currencyService() {
        return service;
    }

    static final class TestConvertible implements Convertible {

        private final String from;
        private final String to;

        TestConvertible(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }
    }
}

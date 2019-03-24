package com.github.ddl0x0d.calculator.common.validation;

import com.github.ddl0x0d.calculator.common.dto.Convertible;
import com.github.ddl0x0d.calculator.currency.CurrencyService;
import com.github.ddl0x0d.calculator.test.CurrencyTestSupport;
import com.github.ddl0x0d.calculator.test.validation.ValidationTestSupport;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.Validator;

@ExtendWith(SpringExtension.class)
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

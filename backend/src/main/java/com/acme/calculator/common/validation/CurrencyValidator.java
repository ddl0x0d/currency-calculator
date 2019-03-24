package com.acme.calculator.common.validation;

import com.acme.calculator.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
class CurrencyValidator implements ConstraintValidator<Currency, String> {

    private static final Pattern PATTERN = Pattern.compile(Currency.REGEX);

    private final CurrencyService service;

    @Override
    public void initialize(Currency constraintAnnotation) {
        // no initialization required
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null
            || !PATTERN.matcher(value).matches()
            || service.getCurrencies().contains(value);
    }
}

package com.github.ddl0x0d.calculator.common.validation;

import com.github.ddl0x0d.calculator.common.dto.Convertible;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

import static java.util.Objects.isNull;

@Component
class DifferentCurrenciesValidator implements ConstraintValidator<DifferentCurrencies, Convertible> {

    @Override
    public void initialize(DifferentCurrencies constraintAnnotation) {
        // no initialization required
    }

    @Override
    public boolean isValid(Convertible value, ConstraintValidatorContext context) {
        String from = value.getFrom();
        String to = value.getTo();
        return isNull(from) || isNull(to) || !Objects.equals(from, to);
    }
}

package com.github.ddl0x0d.calculator.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.github.ddl0x0d.calculator.common.validation.ErrorCodes.CURRENCY_SAME;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = DifferentCurrenciesValidator.class)
public @interface DifferentCurrencies {

    String message() default CURRENCY_SAME;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

package com.github.ddl0x0d.calculator.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.github.ddl0x0d.calculator.common.validation.ErrorCodes.CURRENCY_INVALID;
import static com.github.ddl0x0d.calculator.common.validation.ErrorCodes.CURRENCY_UNSUPPORTED;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD})
@Retention(RUNTIME)
@Documented
@Pattern(regexp = Currency.REGEX, message = CURRENCY_INVALID)
@Constraint(validatedBy = CurrencyValidator.class)
public @interface Currency {

    String REGEX = "[A-Z]{3}";

    String message() default CURRENCY_UNSUPPORTED;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

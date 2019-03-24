package com.acme.calculator.fee.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.acme.calculator.common.validation.ErrorCodes.FEE_DUPLICATE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = UniqueFeeValidator.class)
public @interface UniqueFee {

    String message() default FEE_DUPLICATE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

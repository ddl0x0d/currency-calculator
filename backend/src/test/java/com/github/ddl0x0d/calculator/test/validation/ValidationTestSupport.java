package com.github.ddl0x0d.calculator.test.validation;

import org.assertj.core.api.AbstractListAssert;
import org.assertj.core.api.ObjectAssert;
import org.assertj.core.groups.Tuple;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public interface ValidationTestSupport {

    default AbstractListAssert<?, List<? extends Tuple>, Tuple, ObjectAssert<Tuple>> assertThatViolationsFor(Object object,
                                                                                                             Class<?>... groups) {
        Set<ConstraintViolation<Object>> violations = validator().validate(object, groups);

        return assertThat(violations).extracting(violation -> tuple(
            violation.getPropertyPath().toString(),
            violation.getMessage()));
    }

    Validator validator();

}

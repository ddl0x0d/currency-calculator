package com.github.ddl0x0d.calculator.fee.validation;

import com.github.ddl0x0d.calculator.fee.data.FeeRepository;
import com.github.ddl0x0d.calculator.fee.dto.FeeRequest;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
class UniqueFeeValidator implements ConstraintValidator<UniqueFee, FeeRequest> {

    private final FeeRepository repository;

    UniqueFeeValidator(FeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(UniqueFee constraintAnnotation) {
        // no initialization required
    }

    @Override
    public boolean isValid(FeeRequest value, ConstraintValidatorContext context) {
        return repository.findBy(value.getFrom(), value.getTo()).isEmpty();
    }
}

package com.github.ddl0x0d.calculator.fee.validation;

import com.github.ddl0x0d.calculator.fee.data.FeeRepository;
import com.github.ddl0x0d.calculator.test.validation.ValidationTestSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.Validator;
import java.util.Optional;

import static com.github.ddl0x0d.calculator.test.Constants.FROM;
import static com.github.ddl0x0d.calculator.test.Constants.TO;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.fee;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.feeRequest;
import static com.github.ddl0x0d.calculator.test.validation.ValidationMessages.feeDuplicate;
import static java.util.Optional.empty;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(ValidationAutoConfiguration.class)
public class UniqueFeeValidatorTest implements ValidationTestSupport {

    @Autowired
    private Validator validator;

    @MockBean
    private FeeRepository repository;

    @Override
    public Validator validator() {
        return validator;
    }

    @Test
    public void validate_invalid_duplicate() {
        when(repository.findBy(FROM, TO)).thenReturn(Optional.of(fee("foo")));

        assertThatViolationsFor(feeRequest(), FeeChecks.class)
            .containsExactly(tuple("", feeDuplicate("EUR", "GBP")));
    }

    @Test
    public void validate_valid_unique() {
        when(repository.findBy(FROM, TO)).thenReturn(empty());

        assertThatViolationsFor(feeRequest(), FeeChecks.class).isEmpty();
    }
}

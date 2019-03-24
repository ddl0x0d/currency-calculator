package com.acme.calculator.fee.validation;

import com.acme.calculator.fee.data.FeeRepository;
import com.acme.calculator.test.validation.ValidationTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validator;
import java.util.Optional;

import static com.acme.calculator.test.Constants.FROM;
import static com.acme.calculator.test.Constants.TO;
import static com.acme.calculator.test.DataTransferObjects.fee;
import static com.acme.calculator.test.DataTransferObjects.feeRequest;
import static com.acme.calculator.test.validation.ValidationMessages.feeDuplicate;
import static java.util.Optional.empty;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
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

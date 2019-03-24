package com.github.ddl0x0d.calculator.fee.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.github.ddl0x0d.calculator.test.Constants.FROM;
import static com.github.ddl0x0d.calculator.test.Constants.TO;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.fee;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
abstract class AbstractFeeRepositoryTest {

    @Autowired
    private FeeRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void findAll_save() {
        assertThat(repository.findAll()).isEmpty();

        Fee fee = createFee();
        assertThat(fee.getId()).isNotNull();

        assertThat(repository.findAll()).containsExactly(fee);
    }

    @Test
    public void findBy_missing() {
        Optional<Fee> result = repository.findBy(FROM, TO);

        assertThat(result).isEmpty();
    }

    @Test
    public void findBy_mismatch() {
        createFee();

        Optional<Fee> result = repository.findBy(TO, FROM);

        assertThat(result).isEmpty();
    }

    @Test
    public void findBy_match() {
        Fee fee = createFee();

        Optional<Fee> result = repository.findBy(FROM, TO);

        assertThat(result).contains(fee);
    }

    @Test
    public void delete_missing() {
        boolean result = repository.delete("foo");

        assertThat(result).isFalse();
    }

    @Test
    public void delete_mismatch() {
        String id = createFee().getId();

        boolean result = repository.delete(id + "_");

        assertThat(result).isFalse();
    }

    @Test
    public void delete_match() {
        String id = createFee().getId();

        boolean result = repository.delete(id);

        assertThat(result).isTrue();
    }

    private Fee createFee() {
        return repository.save(fee(null));
    }
}

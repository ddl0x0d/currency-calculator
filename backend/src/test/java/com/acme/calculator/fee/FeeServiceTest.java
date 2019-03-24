package com.acme.calculator.fee;

import com.acme.calculator.fee.data.Fee;
import com.acme.calculator.fee.data.FeeRepository;
import com.acme.calculator.fee.dto.FeeRequest;
import com.acme.calculator.fee.dto.FeeResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.acme.calculator.test.Constants.AMOUNT;
import static com.acme.calculator.test.Constants.FROM;
import static com.acme.calculator.test.Constants.TO;
import static com.acme.calculator.test.DataTransferObjects.fee;
import static com.acme.calculator.test.DataTransferObjects.feeRequest;
import static com.acme.calculator.test.DataTransferObjects.feeResponse;
import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FeeServiceTest {

    @InjectMocks
    private FeeService service;

    @Mock private FeeMapper mapper;
    @Mock private FeeRepository repository;
    @Mock private FeeProperties properties;

    @Test
    public void list() {
        List<Fee> fees = asList(fee("foo"), fee("bar"));
        List<FeeResponse> dtos = asList(feeResponse("foo"), feeResponse("bar"));
        when(repository.findAll()).thenReturn(fees);
        when(mapper.toResponses(fees)).thenReturn(dtos);

        List<FeeResponse> result = service.list();

        assertThat(result).isSameAs(dtos);
    }

    @Test
    public void add() {
        FeeRequest request = feeRequest();
        Fee unsaved = fee(null);
        Fee saved = fee("foo");
        FeeResponse response = feeResponse("foo");

        when(mapper.fromRequest(request)).thenReturn(unsaved);
        when(repository.save(unsaved)).thenReturn(saved);
        when(mapper.toResponse(saved)).thenReturn(response);

        FeeResponse result = service.add(request);

        assertThat(result).isSameAs(response);
    }

    @Test
    public void find_found() {
        Fee fee = fee("foo");
        when(repository.findBy(FROM, TO)).thenReturn(Optional.of(fee));

        Fee result = service.find(FROM, TO);

        assertThat(result).isSameAs(fee);
    }

    @Test
    public void find_default() {
        when(repository.findBy(FROM, TO)).thenReturn(empty());
        when(properties.getDefaultAmount()).thenReturn(AMOUNT);

        Fee result = service.find(FROM, TO);

        assertThat(result).isEqualTo(Fee.builder()
            .id(null)
            .from(FROM)
            .to(TO)
            .amount(AMOUNT)
            .build());
    }

    @Test
    public void delete() {
        when(repository.delete("foo")).thenReturn(true);

        boolean result = service.remove("foo");

        assertThat(result).isTrue();
    }
}

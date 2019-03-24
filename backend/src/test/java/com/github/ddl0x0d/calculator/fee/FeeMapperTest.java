package com.github.ddl0x0d.calculator.fee;

import com.github.ddl0x0d.calculator.fee.data.Fee;
import com.github.ddl0x0d.calculator.fee.dto.FeeRequest;
import com.github.ddl0x0d.calculator.fee.dto.FeeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.github.ddl0x0d.calculator.test.DataTransferObjects.fee;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.feeRequest;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.feeResponse;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = FeeMapperImpl.class)
public class FeeMapperTest {

    @Autowired
    private FeeMapper mapper;

    @Test
    public void fromRequest_null() {
        Fee result = mapper.fromRequest(null);

        assertThat(result).isNull();
    }

    @Test
    public void fromRequest_happyPath() {
        FeeRequest request = feeRequest();

        Fee result = mapper.fromRequest(request);

        assertThat(result).isEqualTo(fee(null));
    }

    @Test
    public void toResponses_empty() {
        List<Fee> fees = emptyList();

        List<FeeResponse> result = mapper.toResponses(fees);

        assertThat(result).isEmpty();
    }

    @Test
    public void toResponses_happyPath() {
        List<Fee> fees = asList(fee("foo"), fee("bar"));

        List<FeeResponse> result = mapper.toResponses(fees);

        assertThat(result).containsExactly(feeResponse("foo"), feeResponse("bar"));
    }

    @Test
    public void toResponse_null() {
        FeeResponse result = mapper.toResponse(null);

        assertThat(result).isNull();
    }

    @Test
    public void toResponse_happyPath() {
        Fee fee = fee("foo");

        FeeResponse result = mapper.toResponse(fee);

        assertThat(result).isEqualTo(feeResponse("foo"));
    }
}

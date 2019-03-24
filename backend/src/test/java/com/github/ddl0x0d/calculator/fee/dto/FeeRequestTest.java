package com.github.ddl0x0d.calculator.fee.dto;

import com.github.ddl0x0d.calculator.test.json.AbstractJsonTest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static com.github.ddl0x0d.calculator.test.DataTransferObjects.feeRequest;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.feeRequestJson;

public class FeeRequestTest extends AbstractJsonTest {

    @Test
    public void deserialization() throws Exception {
        JSONObject json = feeRequestJson();
        FeeRequest dto = feeRequest();

        assertThatDeserialization(json, FeeRequest.class).isEqualTo(dto);
    }
}

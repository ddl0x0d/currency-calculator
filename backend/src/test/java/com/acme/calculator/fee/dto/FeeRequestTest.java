package com.acme.calculator.fee.dto;

import com.acme.calculator.test.json.AbstractJsonTest;
import org.json.JSONObject;
import org.junit.Test;

import static com.acme.calculator.test.DataTransferObjects.feeRequest;
import static com.acme.calculator.test.DataTransferObjects.feeRequestJson;

public class FeeRequestTest extends AbstractJsonTest {

    @Test
    public void deserialization() throws Exception {
        JSONObject json = feeRequestJson();
        FeeRequest dto = feeRequest();

        assertThatDeserialization(json, FeeRequest.class).isEqualTo(dto);
    }
}

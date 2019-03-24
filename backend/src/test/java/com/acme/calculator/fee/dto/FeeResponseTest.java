package com.acme.calculator.fee.dto;

import com.acme.calculator.test.json.AbstractJsonTest;
import org.json.JSONObject;
import org.junit.Test;

import static com.acme.calculator.test.DataTransferObjects.feeResponse;
import static com.acme.calculator.test.DataTransferObjects.feeResponseJson;

public class FeeResponseTest extends AbstractJsonTest {

    @Test
    public void serialization() throws Exception {
        FeeResponse dto = feeResponse("foo");
        JSONObject json = feeResponseJson("foo");

        assertSerialization(dto, json);
    }
}

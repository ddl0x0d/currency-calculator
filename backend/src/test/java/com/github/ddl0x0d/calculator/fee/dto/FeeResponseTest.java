package com.github.ddl0x0d.calculator.fee.dto;

import com.github.ddl0x0d.calculator.test.json.AbstractJsonTest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static com.github.ddl0x0d.calculator.test.DataTransferObjects.feeResponse;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.feeResponseJson;

public class FeeResponseTest extends AbstractJsonTest {

    @Test
    public void serialization() throws Exception {
        FeeResponse dto = feeResponse("foo");
        JSONObject json = feeResponseJson("foo");

        assertSerialization(dto, json);
    }
}

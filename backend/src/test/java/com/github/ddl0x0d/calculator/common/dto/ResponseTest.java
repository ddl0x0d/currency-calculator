package com.github.ddl0x0d.calculator.common.dto;

import com.github.ddl0x0d.calculator.test.json.AbstractJsonTest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static com.github.ddl0x0d.calculator.test.DataTransferObjects.response;

public class ResponseTest extends AbstractJsonTest {

    @Test
    public void serialization() throws Exception {
        String data = "data";
        Response<String> dto = new Response<>(data);
        JSONObject json = response(data);

        assertSerialization(dto, json);
    }
}

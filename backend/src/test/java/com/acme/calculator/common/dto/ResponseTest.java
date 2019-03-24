package com.acme.calculator.common.dto;

import com.acme.calculator.test.json.AbstractJsonTest;
import org.json.JSONObject;
import org.junit.Test;

import static com.acme.calculator.test.DataTransferObjects.response;

public class ResponseTest extends AbstractJsonTest {

    @Test
    public void serialization() throws Exception {
        String data = "data";
        Response<String> dto = new Response<>(data);
        JSONObject json = response(data);

        assertSerialization(dto, json);
    }
}

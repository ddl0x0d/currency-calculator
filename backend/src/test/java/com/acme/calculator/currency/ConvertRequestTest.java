package com.acme.calculator.currency;

import com.acme.calculator.test.json.AbstractJsonTest;
import org.json.JSONObject;
import org.junit.Test;

import static com.acme.calculator.test.DataTransferObjects.convertRequest;
import static com.acme.calculator.test.DataTransferObjects.convertRequestJson;

public class ConvertRequestTest extends AbstractJsonTest {

    @Test
    public void deserialization() throws Exception {
        JSONObject json = convertRequestJson();
        ConvertRequest dto = convertRequest();

        assertThatDeserialization(json, ConvertRequest.class).isEqualTo(dto);
    }
}

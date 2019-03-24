package com.github.ddl0x0d.calculator.currency;

import com.github.ddl0x0d.calculator.test.json.AbstractJsonTest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static com.github.ddl0x0d.calculator.test.DataTransferObjects.convertRequest;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.convertRequestJson;

public class ConvertRequestTest extends AbstractJsonTest {

    @Test
    public void deserialization() throws Exception {
        JSONObject json = convertRequestJson();
        ConvertRequest dto = convertRequest();

        assertThatDeserialization(json, ConvertRequest.class).isEqualTo(dto);
    }
}

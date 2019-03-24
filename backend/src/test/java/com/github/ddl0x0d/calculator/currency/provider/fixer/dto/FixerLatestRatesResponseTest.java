package com.github.ddl0x0d.calculator.currency.provider.fixer.dto;

import com.github.ddl0x0d.calculator.test.json.AbstractJsonTest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static com.github.ddl0x0d.calculator.test.DataTransferObjects.fixerLatestRatesResponse;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.fixerLatestRatesResponseJson;

public class FixerLatestRatesResponseTest extends AbstractJsonTest {

    @Test
    public void deserialization() throws Exception {
        JSONObject json = fixerLatestRatesResponseJson();
        FixerLatestRatesResponse dto = fixerLatestRatesResponse();

        assertThatDeserialization(json, FixerLatestRatesResponse.class).isEqualTo(dto);
    }
}

package com.acme.calculator.currency.provider.fixer.dto;

import com.acme.calculator.test.json.AbstractJsonTest;
import org.json.JSONObject;
import org.junit.Test;

import static com.acme.calculator.test.DataTransferObjects.fixerLatestRatesResponse;
import static com.acme.calculator.test.DataTransferObjects.fixerLatestRatesResponseJson;

public class FixerLatestRatesResponseTest extends AbstractJsonTest {

    @Test
    public void deserialization() throws Exception {
        JSONObject json = fixerLatestRatesResponseJson();
        FixerLatestRatesResponse dto = fixerLatestRatesResponse();

        assertThatDeserialization(json, FixerLatestRatesResponse.class).isEqualTo(dto);
    }
}

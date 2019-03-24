package com.acme.calculator.currency.provider.fixer.dto;

import com.acme.calculator.test.json.AbstractJsonTest;
import org.json.JSONObject;
import org.junit.Test;

import static com.acme.calculator.test.Constants.CURRENCIES;
import static com.acme.calculator.test.DataTransferObjects.fixerSymbolsResponse;
import static com.acme.calculator.test.DataTransferObjects.fixerSymbolsResponseJson;

public class FixerSymbolsResponseTest extends AbstractJsonTest {

    @Test
    public void deserialization() throws Exception {
        JSONObject json = fixerSymbolsResponseJson(CURRENCIES);
        FixerSymbolsResponse dto = fixerSymbolsResponse(CURRENCIES);

        assertThatDeserialization(json, FixerSymbolsResponse.class).isEqualTo(dto);
    }
}

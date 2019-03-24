package com.github.ddl0x0d.calculator.currency.provider.fixer.dto;

import com.github.ddl0x0d.calculator.test.json.AbstractJsonTest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static com.github.ddl0x0d.calculator.test.Constants.CURRENCIES;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.fixerSymbolsResponse;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.fixerSymbolsResponseJson;

public class FixerSymbolsResponseTest extends AbstractJsonTest {

    @Test
    public void deserialization() throws Exception {
        JSONObject json = fixerSymbolsResponseJson(CURRENCIES);
        FixerSymbolsResponse dto = fixerSymbolsResponse(CURRENCIES);

        assertThatDeserialization(json, FixerSymbolsResponse.class).isEqualTo(dto);
    }
}

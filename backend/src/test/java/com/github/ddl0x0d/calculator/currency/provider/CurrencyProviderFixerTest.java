package com.github.ddl0x0d.calculator.currency.provider;

import com.github.ddl0x0d.calculator.currency.provider.fixer.FixerConfig;
import com.github.ddl0x0d.calculator.currency.provider.fixer.FixerProperties;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.test.web.client.response.DefaultResponseCreator;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import static com.github.ddl0x0d.calculator.Profiles.FIXER;
import static com.github.ddl0x0d.calculator.test.Constants.CURRENCIES;
import static com.github.ddl0x0d.calculator.test.Constants.FROM;
import static com.github.ddl0x0d.calculator.test.Constants.RATE;
import static com.github.ddl0x0d.calculator.test.Constants.TO;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.fixerError;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.fixerLatestRatesResponseJson;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.fixerSymbolsResponseJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

@ActiveProfiles(FIXER)
@Import(FixerConfig.class)
@RestClientTest(CurrencyProviderFixer.class)
@TestPropertySource(properties = "app.fixer.api-key=secret")
public class CurrencyProviderFixerTest {

    @Autowired private CurrencyProvider provider;
    @Autowired private FixerProperties properties;
    @Autowired private MockRestServiceServer server;

    private URI symbolsUri;
    private URI latestRatesUri;

    @BeforeEach
    public void setUp() {
        symbolsUri = properties.getEndpoints().getSymbols();
        latestRatesUri = properties.getEndpoints().getLatestRates();
    }

    @Test
    public void getCurrencies_error() {
        expectFixerRequest(symbolsUri).andRespond(withError());

        assertThatThrownBy(() -> provider.getCurrencies())
            .isInstanceOf(CurrencyProviderException.class);
    }

    @Test
    public void getCurrencies_success() {
        expectFixerRequest(symbolsUri).andRespond(withSuccess(fixerSymbolsResponseJson(CURRENCIES)));

        List<String> result = provider.getCurrencies();

        assertThat(result).containsExactly(CURRENCIES);
    }

    @Test
    public void getRate_error() {
        expectGetLatestRatesRequest().andRespond(withError());

        assertThatThrownBy(() -> provider.getRate(FROM, TO))
            .isInstanceOf(CurrencyProviderException.class);
    }

    @Test
    public void getRate_success() {
        expectGetLatestRatesRequest().andRespond(withSuccess(fixerLatestRatesResponseJson()));

        BigDecimal result = provider.getRate(FROM, TO);

        assertThat(result).isEqualTo(RATE);
    }

    private ResponseActions expectGetLatestRatesRequest() {
        return expectFixerRequest(latestRatesUri)
            .andExpect(queryParam("base", FROM))
            .andExpect(queryParam("symbols", TO));
    }

    private ResponseActions expectFixerRequest(URI uri) {
        return server.expect(requestTo(startsWith(uri.toString())))
            .andExpect(queryParam("access_key", properties.getApiKey()));
    }

    private DefaultResponseCreator withError() {
        return MockRestResponseCreators.withSuccess(fixerError().toString(), APPLICATION_JSON);
    }

    private DefaultResponseCreator withSuccess(JSONObject json) {
        return MockRestResponseCreators.withSuccess(json.toString(), APPLICATION_JSON);
    }
}

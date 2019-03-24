package com.acme.calculator.currency.provider;

import com.acme.calculator.currency.provider.fixer.FixerProperties;
import com.acme.calculator.currency.provider.fixer.dto.FixerLatestRatesResponse;
import com.acme.calculator.currency.provider.fixer.dto.FixerResponse;
import com.acme.calculator.currency.provider.fixer.dto.FixerSymbolsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.function.UnaryOperator;

import static com.acme.calculator.Profiles.FIXER;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.web.util.UriComponentsBuilder.fromUri;

/**
 * @see <a href="https://fixer.io/documentation">documentation</a>
 */
@Primary
@Component
@Profile(FIXER)
@RequiredArgsConstructor
class CurrencyProviderFixer implements CurrencyProvider {

    private final RestTemplate template;
    private final FixerProperties properties;

    @Override
    public List<String> getCurrencies() {
        URI baseUri = properties.getEndpoints().getSymbols();
        FixerSymbolsResponse response = get(baseUri, FixerSymbolsResponse.class, identity());
        return response.getSymbols().keySet().stream().sorted().collect(toList());
    }

    @Override
    public BigDecimal getRate(String from, String to) {
        URI baseUri = properties.getEndpoints().getLatestRates();
        return get(baseUri, FixerLatestRatesResponse.class, uri -> uri
            .queryParam("base", from)
            .queryParam("symbols", to))
            .getRates().get(to);
    }

    private <T extends FixerResponse> T get(URI baseUri, Class<T> responseType,
                                            UnaryOperator<UriComponentsBuilder> operator) {
        UriComponentsBuilder builder = fromUri(baseUri)
            .queryParam("access_key", properties.getApiKey());
        URI uri = operator.apply(builder).build().toUri();
        T body = template.exchange(uri, GET, null, responseType).getBody();
        handleErrors(body);
        return body;
    }

    private <T extends FixerResponse> void handleErrors(T body) {
        if (body == null) {
            throw new CurrencyProviderException("no response");
        }
        if (!body.isSuccess()) {
            throw new CurrencyProviderException(body.getError().getInfo());
        }
    }
}

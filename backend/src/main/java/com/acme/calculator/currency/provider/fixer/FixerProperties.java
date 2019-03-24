package com.acme.calculator.currency.provider.fixer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@Data
@Validated
@ConfigurationProperties("app.fixer")
public class FixerProperties {

    @NotNull
    private String apiKey;

    @Valid
    private final Endpoints endpoints = new Endpoints();

    @Data
    @SuppressWarnings("WeakerAccess")
    public static class Endpoints {

        @NotNull private URI symbols;
        @NotNull private URI latestRates;

    }
}

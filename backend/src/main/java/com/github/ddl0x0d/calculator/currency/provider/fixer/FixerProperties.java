package com.github.ddl0x0d.calculator.currency.provider.fixer;

import lombok.Value;
import lombok.experimental.NonFinal;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@Value
@NonFinal
@Validated
@ConstructorBinding
@ConfigurationProperties("app.fixer")
public class FixerProperties {

    @NotNull String apiKey;
    @Valid Endpoints endpoints;

    @Value
    public static class Endpoints {

        @NotNull URI symbols;
        @NotNull URI latestRates;

    }
}

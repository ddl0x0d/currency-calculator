package com.acme.calculator.currency.provider.fixer.dto;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Value
@Builder
public class FixerLatestRatesResponse implements FixerResponse {

    private boolean success;
    private String base;
    private LocalDate date;

    @Singular
    private Map<String, BigDecimal> rates;
    private FixerError error;

}

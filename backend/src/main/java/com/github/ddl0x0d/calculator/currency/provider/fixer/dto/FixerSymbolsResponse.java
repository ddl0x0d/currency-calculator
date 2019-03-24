package com.github.ddl0x0d.calculator.currency.provider.fixer.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class FixerSymbolsResponse implements FixerResponse {

    private boolean success;
    private Map<String, String> symbols;
    private FixerError error;

}

package com.acme.calculator.fee.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class FeeResponse {

    private String id;
    private String from;
    private String to;
    private BigDecimal amount;

}

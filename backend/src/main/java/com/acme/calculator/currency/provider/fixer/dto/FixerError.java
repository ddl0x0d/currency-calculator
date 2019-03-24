package com.acme.calculator.currency.provider.fixer.dto;

import lombok.Value;

@Value
public class FixerError {

    private int code;
    private String type;
    private String info;

}

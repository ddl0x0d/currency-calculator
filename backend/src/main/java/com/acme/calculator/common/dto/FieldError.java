package com.acme.calculator.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;

import static org.springframework.util.StringUtils.capitalize;

@Value
@EqualsAndHashCode(callSuper = true)
public class FieldError extends Error {

    private String field;

    public FieldError(String field, String message) {
        super(message);
        this.field = capitalize(field);
    }
}

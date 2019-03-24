package com.github.ddl0x0d.calculator.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
public class ErrorResponse extends Response<Void> {

    private List<Error> errors;

    public ErrorResponse(List<Error> errors) {
        super(null);
        this.errors = errors;
    }
}

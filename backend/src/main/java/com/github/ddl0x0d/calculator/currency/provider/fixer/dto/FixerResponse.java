package com.github.ddl0x0d.calculator.currency.provider.fixer.dto;

public interface FixerResponse {

    boolean isSuccess();

    FixerError getError();

}

package com.acme.calculator.currency.provider.fixer.dto;

public interface FixerResponse {

    boolean isSuccess();

    FixerError getError();

}

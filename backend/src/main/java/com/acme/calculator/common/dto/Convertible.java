package com.acme.calculator.common.dto;

import com.acme.calculator.common.validation.ConvertibleChecks;
import com.acme.calculator.common.validation.Currency;
import com.acme.calculator.common.validation.DifferentCurrencies;

import javax.validation.constraints.NotEmpty;

@DifferentCurrencies(groups = ConvertibleChecks.class)
public interface Convertible {

    @NotEmpty
    @Currency(groups = ConvertibleChecks.class)
    String getFrom();

    @NotEmpty
    @Currency(groups = ConvertibleChecks.class)
    String getTo();

}

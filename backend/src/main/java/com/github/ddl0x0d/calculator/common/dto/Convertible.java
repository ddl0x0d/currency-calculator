package com.github.ddl0x0d.calculator.common.dto;

import com.github.ddl0x0d.calculator.common.validation.ConvertibleChecks;
import com.github.ddl0x0d.calculator.common.validation.Currency;
import com.github.ddl0x0d.calculator.common.validation.DifferentCurrencies;

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

package com.acme.calculator.fee.dto;

import com.acme.calculator.common.dto.Convertible;
import com.acme.calculator.fee.validation.FeeChecks;
import com.acme.calculator.fee.validation.UniqueFee;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Value
@Builder
@UniqueFee(groups = FeeChecks.class)
public class FeeRequest implements Convertible {

    private String from;
    private String to;

    @NotNull
    @PositiveOrZero
    private BigDecimal amount;

}

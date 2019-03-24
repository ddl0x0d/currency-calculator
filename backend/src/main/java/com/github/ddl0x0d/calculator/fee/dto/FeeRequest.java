package com.github.ddl0x0d.calculator.fee.dto;

import com.github.ddl0x0d.calculator.common.dto.Convertible;
import com.github.ddl0x0d.calculator.fee.validation.FeeChecks;
import com.github.ddl0x0d.calculator.fee.validation.UniqueFee;
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

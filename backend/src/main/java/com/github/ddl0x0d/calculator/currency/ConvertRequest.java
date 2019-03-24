package com.github.ddl0x0d.calculator.currency;

import com.github.ddl0x0d.calculator.common.dto.Convertible;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Value
@Builder
public class ConvertRequest implements Convertible {

    @NotNull
    @Positive
    private BigDecimal amount;

    private String from;
    private String to;

}

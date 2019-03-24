package com.acme.calculator.fee;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Validated
@ConfigurationProperties("app.fee")
public class FeeProperties {

    @NotNull
    private BigDecimal defaultAmount;

}

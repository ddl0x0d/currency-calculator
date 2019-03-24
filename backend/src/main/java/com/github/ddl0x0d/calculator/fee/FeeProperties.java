package com.github.ddl0x0d.calculator.fee;

import lombok.Value;
import lombok.experimental.NonFinal;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
@NonFinal
@Validated
@ConstructorBinding
@ConfigurationProperties("app.fee")
public class FeeProperties {

    @NotNull BigDecimal defaultAmount;

}

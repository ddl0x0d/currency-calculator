package com.github.ddl0x0d.calculator.currency.actuator;

import com.github.ddl0x0d.calculator.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyInfoContributor implements InfoContributor {

    private final CurrencyService service;

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("currencies", service.getCurrencies().size());
    }
}

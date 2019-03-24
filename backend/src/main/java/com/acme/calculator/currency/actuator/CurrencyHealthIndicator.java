package com.acme.calculator.currency.actuator;

import com.acme.calculator.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import static org.springframework.boot.actuate.health.Health.down;
import static org.springframework.boot.actuate.health.Health.up;

@Component("currencies")
@RequiredArgsConstructor
class CurrencyHealthIndicator implements HealthIndicator {

    private final CurrencyService service;

    @Override
    public Health health() {
        return (service.getCurrencies().isEmpty() ? down() : up()).build();
    }
}

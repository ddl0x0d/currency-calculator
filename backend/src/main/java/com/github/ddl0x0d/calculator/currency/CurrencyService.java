package com.github.ddl0x0d.calculator.currency;

import com.github.ddl0x0d.calculator.fee.FeeService;
import com.github.ddl0x0d.calculator.fee.data.Fee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.math.RoundingMode.HALF_UP;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyCache cache;
    private final FeeService feeService;

    public List<String> getCurrencies() {
        return cache.getCurrencies();
    }

    public BigDecimal convert(ConvertRequest request) {
        BigDecimal rate = cache.getRate(request.getFrom(), request.getTo());
        Fee fee = feeService.find(request.getFrom(), request.getTo());
        return request.getAmount().multiply(rate.subtract(fee.getAmount())).setScale(2, HALF_UP);
    }
}

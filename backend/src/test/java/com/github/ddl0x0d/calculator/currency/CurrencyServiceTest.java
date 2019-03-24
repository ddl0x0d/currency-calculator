package com.github.ddl0x0d.calculator.currency;

import com.github.ddl0x0d.calculator.fee.FeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.github.ddl0x0d.calculator.test.Constants.AMOUNT;
import static com.github.ddl0x0d.calculator.test.Constants.CURRENCIES;
import static com.github.ddl0x0d.calculator.test.Constants.FEE;
import static com.github.ddl0x0d.calculator.test.Constants.FROM;
import static com.github.ddl0x0d.calculator.test.Constants.RATE;
import static com.github.ddl0x0d.calculator.test.Constants.TO;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.convertRequest;
import static com.github.ddl0x0d.calculator.test.DataTransferObjects.fee;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @InjectMocks
    private CurrencyService service;

    @Mock private CurrencyCache cache;
    @Mock private FeeService feeService;

    @Test
    public void getCurrencies() {
        when(cache.getCurrencies()).thenReturn(asList(CURRENCIES));

        List<String> result = service.getCurrencies();

        assertThat(result).containsExactly(CURRENCIES);
    }

    @Test
    public void convert() {
        ConvertRequest request = convertRequest();
        when(cache.getRate(FROM, TO)).thenReturn(RATE);
        when(feeService.find(FROM, TO)).thenReturn(fee(null));

        BigDecimal result = service.convert(request);

        assertThat(result).isEqualTo(AMOUNT.multiply(RATE.subtract(FEE)));
    }
}

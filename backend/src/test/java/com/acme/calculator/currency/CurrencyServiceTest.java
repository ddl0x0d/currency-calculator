package com.acme.calculator.currency;

import com.acme.calculator.fee.FeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static com.acme.calculator.test.Constants.AMOUNT;
import static com.acme.calculator.test.Constants.CURRENCIES;
import static com.acme.calculator.test.Constants.FEE;
import static com.acme.calculator.test.Constants.FROM;
import static com.acme.calculator.test.Constants.RATE;
import static com.acme.calculator.test.Constants.TO;
import static com.acme.calculator.test.DataTransferObjects.convertRequest;
import static com.acme.calculator.test.DataTransferObjects.fee;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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

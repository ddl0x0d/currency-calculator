package com.acme.calculator.currency;

import com.acme.calculator.currency.provider.CurrencyProviderException;
import com.acme.calculator.test.CurrencyTestSupport;
import com.acme.calculator.test.web.WebMvcTestSupport;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.acme.calculator.test.Constants.AMOUNT;
import static com.acme.calculator.test.Constants.CONVERSION;
import static com.acme.calculator.test.Constants.CURRENCIES;
import static com.acme.calculator.test.Constants.EUR;
import static com.acme.calculator.test.Constants.FROM;
import static com.acme.calculator.test.Constants.GBP;
import static com.acme.calculator.test.Constants.TO;
import static com.acme.calculator.test.DataTransferObjects.convertRequest;
import static com.acme.calculator.test.DataTransferObjects.convertRequestJson;
import static com.acme.calculator.test.DataTransferObjects.error;
import static com.acme.calculator.test.DataTransferObjects.fieldError;
import static com.acme.calculator.test.validation.ValidationMessages.currencyInvalid;
import static com.acme.calculator.test.validation.ValidationMessages.currencyProvider;
import static com.acme.calculator.test.validation.ValidationMessages.currencySame;
import static com.acme.calculator.test.validation.ValidationMessages.currencyUnsupported;
import static com.acme.calculator.test.validation.ValidationMessages.notEmpty;
import static com.acme.calculator.test.validation.ValidationMessages.notNull;
import static com.acme.calculator.test.validation.ValidationMessages.unexpectedError;
import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest implements WebMvcTestSupport, CurrencyTestSupport {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CurrencyService service;

    @Override
    public MockMvc mvc() {
        return mvc;
    }

    @Override
    public CurrencyService currencyService() {
        return service;
    }

    @Test
    public void list_ok_missing() throws Exception {
        when(service.getCurrencies()).thenReturn(emptyList());

        perform(GET, "/currencies")
            .andExpect(OK)
            .response();
    }

    @Test
    public void list_ok_present() throws Exception {
        when(service.getCurrencies()).thenReturn(asList(EUR, GBP));

        perform(GET, "/currencies")
            .andExpect(OK)
            .response(EUR, GBP);
    }

    @Test
    public void convert_badRequest_nulls() throws Exception {
        perform(POST, "/currencies/convert")
            .request(new JSONObject())
            .andExpect(BAD_REQUEST)
            .errors(
                fieldError("amount", notNull()),
                fieldError("from", notEmpty()),
                fieldError("to", notEmpty()));

        verifyZeroInteractions(service);
    }

    @Test
    public void convert_badRequest_amount_format() throws Exception {
        perform(POST, "/currencies/convert")
            .request(convertRequestJson()
                .put("amount", "hundred"))
            .andExpect(BAD_REQUEST)
            .errors(error("JSON parse error: Cannot deserialize value of type " +
                "`java.math.BigDecimal` from String \"hundred\": not a valid representation"));

        verifyZeroInteractions(service);
    }

    @Test
    public void convert_badRequest_amount_negative() throws Exception {
        perform(POST, "/currencies/convert")
            .request(convertRequestJson()
                .put("amount", AMOUNT.negate()))
            .andExpect(BAD_REQUEST)
            .errors(fieldError("amount", "must be greater than 0"));

        verifyZeroInteractions(service);
    }

    @Test
    public void convert_badRequest_amount_zero() throws Exception {
        perform(POST, "/currencies/convert")
            .request(convertRequestJson()
                .put("amount", ZERO))
            .andExpect(BAD_REQUEST)
            .errors(fieldError("amount", "must be greater than 0"));

        verifyZeroInteractions(service);
    }

    @Test
    public void convert_badRequest_currencyInvalid() throws Exception {
        perform(POST, "/currencies/convert")
            .request(convertRequestJson()
                .put("from", "euro")
                .put("to", "pound"))
            .andExpect(BAD_REQUEST)
            .errors(
                fieldError("from", currencyInvalid("euro")),
                fieldError("to", currencyInvalid("pound")));

        verifyZeroInteractions(service);
    }

    @Test
    public void convert_badRequest_currencyUnsupported() throws Exception {
        perform(POST, "/currencies/convert")
            .request(convertRequestJson())
            .andExpect(BAD_REQUEST)
            .errors(
                fieldError("from", currencyUnsupported(FROM)),
                fieldError("to", currencyUnsupported(TO)));

        verify(service, never()).convert(any(ConvertRequest.class));
    }

    @Test
    public void convert_badRequest_currencySame() throws Exception {
        expectCurrencies(CURRENCIES);

        perform(POST, "/currencies/convert")
            .request(new JSONObject()
                .put("amount", AMOUNT)
                .put("from", EUR)
                .put("to", EUR))
            .andExpect(BAD_REQUEST)
            .errors(error(currencySame()));

        verify(service, never()).convert(any(ConvertRequest.class));
    }

    @Test
    public void convert_methodNotAllowed() throws Exception {
        perform(PUT, "/currencies/convert")
            .request(convertRequestJson())
            .andExpect(METHOD_NOT_ALLOWED)
            .errors(error("Request method 'PUT' not supported"));

        verifyZeroInteractions(service);
    }

    @Test
    public void convert_unsupportedMediaType() throws Exception {
        perform(POST, "/currencies/convert")
            .andExpect(UNSUPPORTED_MEDIA_TYPE)
            .errors(error("Content type '' not supported"));

        verifyZeroInteractions(service);
    }

    @Test
    public void convert_internalServerError_currencyProvider() throws Exception {
        String error = "error";
        expectCurrencies(CURRENCIES);
        when(service.convert(convertRequest()))
            .thenThrow(new CurrencyProviderException(error));

        perform(POST, "/currencies/convert")
            .request(convertRequestJson())
            .andExpect(INTERNAL_SERVER_ERROR)
            .errors(error(currencyProvider(error)));
    }

    @Test
    public void convert_internalServerError_unexpectedError() throws Exception {
        String error = "error";
        expectCurrencies(CURRENCIES);
        when(service.convert(convertRequest()))
            .thenThrow(new RuntimeException(error));

        perform(POST, "/currencies/convert")
            .request(convertRequestJson())
            .andExpect(INTERNAL_SERVER_ERROR)
            .errors(error(unexpectedError(error)));
    }

    @Test
    public void convert_ok() throws Exception {
        expectCurrencies(CURRENCIES);
        when(service.convert(convertRequest())).thenReturn(CONVERSION);

        perform(POST, "/currencies/convert")
            .request(convertRequestJson())
            .andExpect(OK)
            .response(CONVERSION);
    }
}

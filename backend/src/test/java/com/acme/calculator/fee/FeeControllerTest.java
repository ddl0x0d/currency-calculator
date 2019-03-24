package com.acme.calculator.fee;

import com.acme.calculator.currency.CurrencyService;
import com.acme.calculator.currency.provider.CurrencyProviderException;
import com.acme.calculator.fee.data.FeeRepository;
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

import java.util.Optional;

import static com.acme.calculator.test.Constants.CURRENCIES;
import static com.acme.calculator.test.Constants.EUR;
import static com.acme.calculator.test.Constants.FEE;
import static com.acme.calculator.test.Constants.FROM;
import static com.acme.calculator.test.Constants.TO;
import static com.acme.calculator.test.DataTransferObjects.error;
import static com.acme.calculator.test.DataTransferObjects.fee;
import static com.acme.calculator.test.DataTransferObjects.feeRequest;
import static com.acme.calculator.test.DataTransferObjects.feeRequestJson;
import static com.acme.calculator.test.DataTransferObjects.feeResponse;
import static com.acme.calculator.test.DataTransferObjects.feeResponseJson;
import static com.acme.calculator.test.DataTransferObjects.fieldError;
import static com.acme.calculator.test.validation.ValidationMessages.currencyInvalid;
import static com.acme.calculator.test.validation.ValidationMessages.currencyProvider;
import static com.acme.calculator.test.validation.ValidationMessages.currencySame;
import static com.acme.calculator.test.validation.ValidationMessages.currencyUnsupported;
import static com.acme.calculator.test.validation.ValidationMessages.feeDuplicate;
import static com.acme.calculator.test.validation.ValidationMessages.notEmpty;
import static com.acme.calculator.test.validation.ValidationMessages.notNull;
import static com.acme.calculator.test.validation.ValidationMessages.unexpectedError;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;

@RunWith(SpringRunner.class)
@WebMvcTest(FeeController.class)
public class FeeControllerTest implements WebMvcTestSupport, CurrencyTestSupport {

    @Autowired
    private MockMvc mvc;

    @MockBean private FeeService feeService;
    @MockBean private FeeRepository feeRepository;
    @MockBean private CurrencyService currencyService;

    @Override
    public MockMvc mvc() {
        return mvc;
    }

    @Override
    public CurrencyService currencyService() {
        return currencyService;
    }

    @Test
    public void list_ok_missing() throws Exception {
        when(feeService.list()).thenReturn(emptyList());

        perform(GET, "/fees")
            .andExpect(OK)
            .response();
    }

    @Test
    public void list_ok_present() throws Exception {
        when(feeService.list()).thenReturn(asList(feeResponse("foo"), feeResponse("bar")));

        perform(GET, "/fees")
            .andExpect(OK)
            .response(
                feeResponseJson("foo"),
                feeResponseJson("bar"));
    }

    @Test
    public void add_badRequest_nulls() throws Exception {
        perform(POST, "/fees")
            .request(new JSONObject())
            .andExpect(BAD_REQUEST)
            .errors(
                fieldError("from", notEmpty()),
                fieldError("to", notEmpty()),
                fieldError("amount", notNull()));

        verifyZeroInteractions(currencyService, feeRepository, feeService);
    }

    @Test
    public void add_badRequest_amount_format() throws Exception {
        perform(POST, "/fees")
            .request(feeRequestJson()
                .put("amount", "hundred"))
            .andExpect(BAD_REQUEST)
            .errors(error("JSON parse error: Cannot deserialize value of type " +
                "`java.math.BigDecimal` from String \"hundred\": not a valid representation"));

        verifyZeroInteractions(currencyService, feeRepository, feeService);
    }

    @Test
    public void add_badRequest_amount_negative() throws Exception {
        perform(POST, "/fees")
            .request(feeRequestJson()
                .put("amount", FEE.negate()))
            .andExpect(BAD_REQUEST)
            .errors(fieldError("amount", "must be greater than or equal to 0"));

        verifyZeroInteractions(currencyService, feeRepository, feeService);
    }

    @Test
    public void add_badRequest_currencyInvalid() throws Exception {
        perform(POST, "/fees")
            .request(feeRequestJson()
                .put("from", "euro")
                .put("to", "pound"))
            .andExpect(BAD_REQUEST)
            .errors(
                fieldError("from", currencyInvalid("euro")),
                fieldError("to", currencyInvalid("pound")));

        verifyZeroInteractions(currencyService, feeRepository, feeService);
    }

    @Test
    public void add_badRequest_currencyUnsupported() throws Exception {
        expectCurrencies();

        perform(POST, "/fees")
            .request(feeRequestJson())
            .andExpect(BAD_REQUEST)
            .errors(
                fieldError("from", currencyUnsupported(FROM)),
                fieldError("to", currencyUnsupported(TO)));

        verify(currencyService, times(2)).getCurrencies();
        verifyZeroInteractions(feeRepository, feeService);
    }

    @Test
    public void add_badRequest_currencySame() throws Exception {
        expectCurrencies(CURRENCIES);

        perform(POST, "/fees")
            .request(new JSONObject()
                .put("from", EUR)
                .put("to", EUR)
                .put("amount", FEE))
            .andExpect(BAD_REQUEST)
            .errors(error(currencySame()));

        verify(currencyService, times(2)).getCurrencies();
        verifyZeroInteractions(feeRepository, feeService);
    }

    @Test
    public void add_badRequest_feeDuplicate() throws Exception {
        expectCurrencies(CURRENCIES);
        when(feeRepository.findBy(FROM, TO)).thenReturn(Optional.of(fee("foo")));

        perform(POST, "/fees")
            .request(feeRequestJson())
            .andExpect(BAD_REQUEST)
            .errors(error(feeDuplicate(FROM, TO)));

        verifyZeroInteractions(feeService);
        verify(feeRepository, only()).findBy(FROM, TO);
    }

    @Test
    public void add_methodNotAllowed() throws Exception {
        perform(PUT, "/fees")
            .request(feeRequestJson())
            .andExpect(METHOD_NOT_ALLOWED)
            .errors(error("Request method 'PUT' not supported"));

        verifyZeroInteractions(currencyService, feeRepository, feeService);
    }

    @Test
    public void add_unsupportedMediaType() throws Exception {
        perform(POST, "/fees")
            .andExpect(UNSUPPORTED_MEDIA_TYPE)
            .errors(error("Content type '' not supported"));

        verifyZeroInteractions(currencyService, feeRepository, feeService);
    }

    @Test
    public void add_internalServerError_currencyProvider() throws Exception {
        String error = "error";
        expectCurrencies(CURRENCIES);
        when(feeService.add(feeRequest()))
            .thenThrow(new CurrencyProviderException(error));

        perform(POST, "/fees")
            .request(feeRequestJson())
            .andExpect(INTERNAL_SERVER_ERROR)
            .errors(error(currencyProvider(error)));
    }

    @Test
    public void add_internalServerError_unexpectedError() throws Exception {
        String error = "error";
        expectCurrencies(CURRENCIES);
        when(feeService.add(feeRequest()))
            .thenThrow(new RuntimeException(error));

        perform(POST, "/fees")
            .request(feeRequestJson())
            .andExpect(INTERNAL_SERVER_ERROR)
            .errors(error(unexpectedError(error)));
    }

    @Test
    public void add_created() throws Exception {
        expectCurrencies(CURRENCIES);
        when(feeService.add(feeRequest())).thenReturn(feeResponse("foo"));

        perform(POST, "/fees")
            .request(feeRequestJson())
            .andExpect(CREATED)
            .response(feeResponseJson("foo"));
    }

    @Test
    public void remove_notFound() throws Exception {
        when(feeService.remove("foo")).thenReturn(false);

        perform(DELETE, "/fees/{id}", "foo").andRespond(NOT_FOUND);
    }

    @Test
    public void remove_ok() throws Exception {
        when(feeService.remove("foo")).thenReturn(true);

        perform(DELETE, "/fees/{id}", "foo").andRespond(OK);
    }
}

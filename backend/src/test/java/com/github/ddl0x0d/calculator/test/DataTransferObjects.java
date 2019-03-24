package com.github.ddl0x0d.calculator.test;

import com.github.ddl0x0d.calculator.currency.ConvertRequest;
import com.github.ddl0x0d.calculator.currency.provider.fixer.dto.FixerLatestRatesResponse;
import com.github.ddl0x0d.calculator.currency.provider.fixer.dto.FixerSymbolsResponse;
import com.github.ddl0x0d.calculator.fee.data.Fee;
import com.github.ddl0x0d.calculator.fee.dto.FeeRequest;
import com.github.ddl0x0d.calculator.fee.dto.FeeResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Map;

import static com.github.ddl0x0d.calculator.test.Constants.AMOUNT;
import static com.github.ddl0x0d.calculator.test.Constants.FEE;
import static com.github.ddl0x0d.calculator.test.Constants.FROM;
import static com.github.ddl0x0d.calculator.test.Constants.RATE;
import static com.github.ddl0x0d.calculator.test.Constants.TO;
import static java.time.Month.JANUARY;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.json.JSONObject.NULL;
import static org.springframework.util.StringUtils.capitalize;

public abstract class DataTransferObjects {

    /*---------------- Common ----------------*/

    public static JSONObject response(Object data) {
        return new JSONObject().put("data", data);
    }

    public static JSONObject errorResponse(JSONObject... errors) {
        return response(NULL).put("errors", new JSONArray(asList(errors)));
    }

    public static JSONObject error(String message) {
        return new JSONObject()
            .put("message", message);
    }

    public static JSONObject fieldError(String field, String message) {
        return new JSONObject()
            .put("field", capitalize(field))
            .put("message", message);
    }

    /*---------------- Fees ----------------*/

    public static JSONObject feeRequestJson() {
        return new JSONObject()
            .put("from", FROM)
            .put("to", TO)
            .put("amount", FEE);
    }

    public static FeeRequest feeRequest() {
        return FeeRequest.builder()
            .from(FROM)
            .to(TO)
            .amount(FEE)
            .build();
    }

    public static Fee fee(String id) {
        return Fee.builder()
            .id(id)
            .from(FROM)
            .to(TO)
            .amount(FEE)
            .build();
    }

    public static FeeResponse feeResponse(String id) {
        return FeeResponse.builder()
            .id(id)
            .from(FROM)
            .to(TO)
            .amount(FEE)
            .build();
    }

    public static JSONObject feeResponseJson(String id) {
        return new JSONObject()
            .put("id", id)
            .put("from", FROM)
            .put("to", TO)
            .put("amount", FEE);
    }

    /*---------------- Conversion ----------------*/

    public static JSONObject convertRequestJson() {
        return new JSONObject()
            .put("amount", AMOUNT)
            .put("from", FROM)
            .put("to", TO);
    }

    public static ConvertRequest convertRequest() {
        return ConvertRequest.builder()
            .amount(AMOUNT)
            .from(FROM)
            .to(TO)
            .build();
    }

    /*---------------- Fixer ----------------*/

    public static JSONObject fixerError() {
        return new JSONObject()
            .put("success", false)
            .put("error", new JSONObject()
                .put("code", 123)
                .put("type", "error")
                .put("info", "You got an error"));
    }

    public static JSONObject fixerSymbolsResponseJson(String... currencies) {
        Map<String, String> symbols = stream(currencies)
            .collect(toMap(identity(), identity()));
        return new JSONObject()
            .put("success", true)
            .put("symbols", new JSONObject(symbols));
    }

    public static FixerSymbolsResponse fixerSymbolsResponse(String... currencies) {
        Map<String, String> symbols = stream(currencies)
            .collect(toMap(identity(), identity()));
        return FixerSymbolsResponse.builder()
            .success(true)
            .symbols(symbols)
            .error(null)
            .build();
    }

    public static JSONObject fixerLatestRatesResponseJson() {
        return new JSONObject()
            .put("success", true)
            .put("base", FROM)
            .put("date", "2019-01-01")
            .put("rates", new JSONObject().put(TO, RATE));
    }

    public static FixerLatestRatesResponse fixerLatestRatesResponse() {
        return FixerLatestRatesResponse.builder()
            .success(true)
            .base(FROM)
            .date(LocalDate.of(2019, JANUARY, 1))
            .rate(TO, RATE)
            .error(null)
            .build();
    }
}

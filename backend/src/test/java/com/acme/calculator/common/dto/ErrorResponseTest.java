package com.acme.calculator.common.dto;

import com.acme.calculator.test.json.AbstractJsonTest;
import org.json.JSONObject;
import org.junit.Test;

import static com.acme.calculator.test.DataTransferObjects.error;
import static com.acme.calculator.test.DataTransferObjects.errorResponse;
import static com.acme.calculator.test.DataTransferObjects.fieldError;
import static java.util.Arrays.asList;

public class ErrorResponseTest extends AbstractJsonTest {

    private static final String FIELD = "field";
    private static final String ERROR = "error";

    @Test
    public void serialization() throws Exception {
        ErrorResponse dto = new ErrorResponse(asList(
            new FieldError(FIELD, ERROR),
            new Error(ERROR)));
        JSONObject json = errorResponse(
            fieldError(FIELD, ERROR),
            error(ERROR));

        assertSerialization(dto, json);
    }
}

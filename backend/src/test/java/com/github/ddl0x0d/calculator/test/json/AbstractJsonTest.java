package com.github.ddl0x0d.calculator.test.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.ObjectAssert;
import org.json.JSONObject;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@ExtendWith(SpringExtension.class)
public abstract class AbstractJsonTest {

    @Autowired
    private ObjectMapper mapper;

    protected <T> void assertSerialization(T dto, JSONObject json) throws Exception {
        JSONAssert.assertEquals(json.toString(), mapper.writeValueAsString(dto), true);
    }

    protected <T> ObjectAssert<T> assertThatDeserialization(JSONObject json, Class<T> type) throws Exception {
        return assertThat(mapper.readValue(json.toString(), type));
    }
}

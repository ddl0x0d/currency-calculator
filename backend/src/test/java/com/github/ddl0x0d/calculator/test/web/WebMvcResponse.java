package com.github.ddl0x0d.calculator.test.web;

import com.github.ddl0x0d.calculator.test.DataTransferObjects;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.test.web.servlet.ResultActions;

import static com.github.ddl0x0d.calculator.test.DataTransferObjects.errorResponse;
import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class WebMvcResponse {

    private final ResultActions actions;

    WebMvcResponse(ResultActions actions) {
        this.actions = actions;
    }

    public void response(Object data) throws Exception {
        json(DataTransferObjects.response(data), true);
    }

    public void response(Object... data) throws Exception {
        json(DataTransferObjects.response(new JSONArray(asList(data))), true);
    }

    public void errors(JSONObject... errors) throws Exception {
        json(errorResponse(errors), false);
    }

    private void json(JSONObject json, boolean strict) throws Exception {
        actions.andExpect(content().contentType(APPLICATION_JSON));
        actions.andExpect(content().json(json.toString(), strict));
    }

    void noBody() throws Exception {
        actions.andExpect(content().string(""));
    }
}

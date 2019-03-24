package com.acme.calculator.test.web;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WebMvcRequest {

    private final MockMvc mvc;
    private final MockHttpServletRequestBuilder request;

    WebMvcRequest(MockMvc mvc, MockHttpServletRequestBuilder request) {
        this.mvc = mvc;
        this.request = request;
    }

    public WebMvcRequest request(JSONObject json) {
        request.contentType(APPLICATION_JSON_UTF8);
        request.content(json.toString());
        return this;
    }

    public void andRespond(HttpStatus status) throws Exception {
        andExpect(status).noBody();
    }

    public WebMvcResponse andExpect(HttpStatus status) throws Exception {
        ResultActions resultActions = mvc.perform(request);
        resultActions.andExpect(status().is(status.value()));
        return new WebMvcResponse(resultActions);
    }
}

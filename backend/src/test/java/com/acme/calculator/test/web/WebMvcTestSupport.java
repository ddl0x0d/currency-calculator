package com.acme.calculator.test.web;

import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

public interface WebMvcTestSupport {

    default WebMvcRequest perform(HttpMethod method, String template, Object... vars) {
        return new WebMvcRequest(mvc(), request(method, template, vars));
    }

    MockMvc mvc();

}

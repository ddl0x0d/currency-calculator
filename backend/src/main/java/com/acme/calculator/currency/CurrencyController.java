package com.acme.calculator.currency;

import com.acme.calculator.common.dto.Response;
import com.acme.calculator.common.validation.ConvertibleChecksOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currencies")
class CurrencyController {

    private final CurrencyService service;

    @GetMapping
    public Response<List<String>> list() {
        return new Response<>(service.getCurrencies());
    }

    @PostMapping(value = "/convert", consumes = APPLICATION_JSON_VALUE)
    public Response<BigDecimal> convert(@Validated(ConvertibleChecksOrder.class)
                                        @RequestBody ConvertRequest request) {
        return new Response<>(service.convert(request));
    }
}

package com.acme.calculator.fee;

import com.acme.calculator.common.dto.Response;
import com.acme.calculator.fee.dto.FeeRequest;
import com.acme.calculator.fee.dto.FeeResponse;
import com.acme.calculator.fee.validation.FeeChecksOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/fees")
@RequiredArgsConstructor
class FeeController {

    private final FeeService service;

    @GetMapping
    public Response<List<FeeResponse>> list() {
        return new Response<>(service.list());
    }

    @ResponseStatus(CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Response<FeeResponse> add(@Validated(FeeChecksOrder.class)
                                     @RequestBody FeeRequest request) {
        return new Response<>(service.add(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable String id) {
        return service.remove(id) ? ok().build() : notFound().build();
    }
}

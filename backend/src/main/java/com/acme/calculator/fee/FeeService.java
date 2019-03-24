package com.acme.calculator.fee;

import com.acme.calculator.fee.data.Fee;
import com.acme.calculator.fee.data.FeeRepository;
import com.acme.calculator.fee.dto.FeeRequest;
import com.acme.calculator.fee.dto.FeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeeService {

    private final FeeMapper mapper;
    private final FeeRepository repository;
    private final FeeProperties properties;

    public List<FeeResponse> list() {
        return mapper.toResponses(repository.findAll());
    }

    public FeeResponse add(FeeRequest request) {
        Fee fee = mapper.fromRequest(request);
        fee = repository.save(fee);
        return mapper.toResponse(fee);
    }

    public Fee find(String from, String to) {
        return repository.findBy(from, to)
            .orElseGet(() -> Fee.builder()
                .from(from).to(to)
                .amount(properties.getDefaultAmount())
                .build());
    }

    public boolean remove(String id) {
        return repository.delete(id);
    }
}

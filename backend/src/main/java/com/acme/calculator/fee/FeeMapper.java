package com.acme.calculator.fee;

import com.acme.calculator.fee.data.Fee;
import com.acme.calculator.fee.dto.FeeRequest;
import com.acme.calculator.fee.dto.FeeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeeMapper {

    @Mapping(target = "id", ignore = true)
    Fee fromRequest(FeeRequest request);

    List<FeeResponse> toResponses(Iterable<Fee> fees);

    FeeResponse toResponse(Fee fee);

}

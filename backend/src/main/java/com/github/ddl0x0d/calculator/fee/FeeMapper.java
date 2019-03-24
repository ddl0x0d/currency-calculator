package com.github.ddl0x0d.calculator.fee;

import com.github.ddl0x0d.calculator.fee.data.Fee;
import com.github.ddl0x0d.calculator.fee.dto.FeeRequest;
import com.github.ddl0x0d.calculator.fee.dto.FeeResponse;
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

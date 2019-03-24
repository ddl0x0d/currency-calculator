package com.acme.calculator.fee.data;

import com.acme.calculator.common.dto.Convertible;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.math.BigDecimal;

@Data
@Builder
@RedisHash("fee")
public class Fee implements Convertible {

    @Id
    private String id;

    @Indexed
    private String from;

    @Indexed
    private String to;

    private BigDecimal amount;

    boolean matches(String from, String to) {
        return from.equals(this.from) && to.equals(this.to);
    }
}

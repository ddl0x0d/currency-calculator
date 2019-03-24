package com.github.ddl0x0d.calculator.fee.data;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static com.github.ddl0x0d.calculator.Profiles.REDIS;

@Repository
@Profile("!" + REDIS)
class FeeRepositorySimple implements FeeRepository {

    private final AtomicLong ids = new AtomicLong();
    private final Map<String, Fee> fees = new ConcurrentHashMap<>();

    @Override
    public Iterable<Fee> findAll() {
        return fees.values();
    }

    @Override
    public Fee save(Fee fee) {
        String id = ids.incrementAndGet() + "";
        fees.put(id, fee);
        fee.setId(id);
        return fee;
    }

    @Override
    public Optional<Fee> findBy(String from, String to) {
        return fees.values().stream()
            .filter(fee -> fee.matches(from, to))
            .findFirst();
    }

    @Override
    public boolean delete(String id) {
        return fees.remove(id) != null;
    }

    @Override
    public void deleteAll() {
        fees.clear();
    }
}

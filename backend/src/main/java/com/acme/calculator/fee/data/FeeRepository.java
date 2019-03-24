package com.acme.calculator.fee.data;

import java.util.Optional;

public interface FeeRepository {

    Iterable<Fee> findAll();

    Fee save(Fee fee);

    Optional<Fee> findBy(String from, String to);

    boolean delete(String id);

    void deleteAll();

}

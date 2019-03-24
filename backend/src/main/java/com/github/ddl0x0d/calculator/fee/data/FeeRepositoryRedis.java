package com.github.ddl0x0d.calculator.fee.data;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.github.ddl0x0d.calculator.Profiles.REDIS;

@Primary
@Repository
@Profile(REDIS)
@SuppressWarnings("unchecked")
interface FeeRepositoryRedis extends FeeRepository, CrudRepository<Fee, String> {

    @Override
    Fee save(Fee fee);

    @Override
    default Optional<Fee> findBy(String from, String to) {
        return findByFromAndTo(from, to);
    }

    Optional<Fee> findByFromAndTo(String from, String to);

    @Override
    default boolean delete(String id) {
        boolean exists = existsById(id);
        if (exists) {
            deleteById(id);
        }
        return exists;
    }
}

package com.github.ddl0x0d.calculator.fee.data;

import com.github.ddl0x0d.calculator.test.containers.RedisTestContainer;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.ActiveProfiles;

import static com.github.ddl0x0d.calculator.Profiles.REDIS;

@DataRedisTest
@RedisTestContainer
@ActiveProfiles(REDIS)
public class FeeRepositoryRedisTest extends AbstractFeeRepositoryTest {

}

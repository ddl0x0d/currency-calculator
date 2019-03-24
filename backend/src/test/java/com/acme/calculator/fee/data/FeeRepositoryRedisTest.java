package com.acme.calculator.fee.data;

import com.acme.calculator.test.containers.RedisTestContainer;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.ActiveProfiles;

import static com.acme.calculator.Profiles.REDIS;

@DataRedisTest
@RedisTestContainer
@ActiveProfiles(REDIS)
public class FeeRepositoryRedisTest extends AbstractFeeRepositoryTest {

}

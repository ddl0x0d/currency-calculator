package com.acme.calculator.currency;

import com.acme.calculator.test.containers.RedisTestContainer;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.test.context.ActiveProfiles;

import static com.acme.calculator.Profiles.REDIS;

@RedisTestContainer
@ActiveProfiles(REDIS)
@AutoConfigureCache(cacheProvider = CacheType.REDIS)
@ImportAutoConfiguration(RedisAutoConfiguration.class)
public class CurrencyRedisCacheTest extends AbstractCurrencyCacheTest {

}

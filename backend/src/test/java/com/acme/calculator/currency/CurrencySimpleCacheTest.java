package com.acme.calculator.currency;

import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;

import static org.springframework.boot.autoconfigure.cache.CacheType.SIMPLE;

@AutoConfigureCache(cacheProvider = SIMPLE)
public class CurrencySimpleCacheTest extends AbstractCurrencyCacheTest {

}

package com.github.ddl0x0d.calculator.test.containers;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@RunTestContainer(image = "redis",
    hostProperty = "spring.redis.host",
    portProperty = "spring.redis.port")
public @interface RedisTestContainer {

    @AliasFor(annotation = RunTestContainer.class)
    String tag() default "6.0-alpine";

    @AliasFor(annotation = RunTestContainer.class)
    int[] ports() default {6379};

}

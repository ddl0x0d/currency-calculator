package com.acme.calculator.test.containers;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RunTestContainer(image = "redis",
    hostProperty = "spring.redis.host",
    portProperty = "spring.redis.port")
public @interface RedisTestContainer {

    @AliasFor(annotation = RunTestContainer.class)
    String tag() default "5-alpine";

    @AliasFor(annotation = RunTestContainer.class)
    int[] ports() default {6379};

}

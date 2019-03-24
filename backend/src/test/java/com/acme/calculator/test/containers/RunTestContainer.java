package com.acme.calculator.test.containers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RunTestContainer {

    String image() default "alpine";

    String tag() default "latest";

    int[] ports() default {};

    String hostProperty();

    String portProperty();

}

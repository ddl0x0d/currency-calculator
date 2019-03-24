package com.github.ddl0x0d.calculator.test.containers;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.testcontainers.containers.GenericContainer;

import java.util.Arrays;

public class TestContainersExecutionListener extends AbstractTestExecutionListener {

    private GenericContainer<?> container;

    @Override
    public void beforeTestClass(TestContext testContext) {
        Class<?> testClass = testContext.getTestClass();
        RunTestContainer annotation = AnnotatedElementUtils.getMergedAnnotation(testClass, RunTestContainer.class);
        if (annotation != null) {
            String dockerImageName = annotation.image() + ":" + annotation.tag();
            container = new GenericContainer<>(dockerImageName);
            Integer[] ports = Arrays.stream(annotation.ports())
                .boxed().toArray(Integer[]::new);
            container.withExposedPorts(ports);
            container.start();

            System.setProperty(annotation.hostProperty(), container.getHost());
            System.setProperty(annotation.portProperty(), container.getFirstMappedPort() + "");
        }
    }

    @Override
    public void afterTestClass(TestContext testContext) {
        if (container != null) {
            container.stop();
        }
    }
}

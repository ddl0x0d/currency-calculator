package com.acme.calculator.fee;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FeeProperties.class)
public class FeeConfig {

}

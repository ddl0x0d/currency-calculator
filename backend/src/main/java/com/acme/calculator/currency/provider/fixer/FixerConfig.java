package com.acme.calculator.currency.provider.fixer;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import static com.acme.calculator.Profiles.FIXER;

@Configuration
@Profile(FIXER)
@EnableConfigurationProperties(FixerProperties.class)
public class FixerConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

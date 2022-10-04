package com.test.demo.Test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class testConfig {

    @Bean
    public TestController testController(TestRepository testRepository) {
        return new TestController(testRepository);
    }
}

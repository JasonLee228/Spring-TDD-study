package com.test.demo;

import com.test.demo.Test.TestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class test {

    @Autowired
    ApplicationContext applicationContext; // ApplicationContext 을 가져와서 빈을 직접 꺼낼 수 있도록 함.

    @Test
    public void beanTest() {

        TestController bean = applicationContext.getBean(TestController.class);

        assertThat(bean).isNotNull();

    }
}

package com.test.demo.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Controller
public class TestController {

    // 1. 생성자를 통해서 주입받는 방법
    private final TestRepository testRepository;

    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    // 2. field 에 @Autowired 를 사용하는 방법
    @Autowired
    private TestRepository testRepository2;


    // 3. Setter 에 @Autowired 를 사용하는 방법
    private TestRepository testRepository3;

    @Autowired
    public void setRepository(TestRepository testRepository3) {
        this.testRepository3 = testRepository3;
    }


}

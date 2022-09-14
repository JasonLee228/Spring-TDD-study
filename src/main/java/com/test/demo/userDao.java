package com.test.demo;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class userDao {

    private static Map<String, String> userDb = new HashMap<>();

    public String insert(String key, String value) {

        userDb.put(key, value);

        System.out.println("key : " + key);
        System.out.println("value : " + value);

        return key;

    }

    public String select(String key) {

        System.out.println(userDb);

        return userDb.get(key);

    }

}

package com.test.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class userDao {

//    private static Map<String, String> userDb = new HashMap<>();
    private static List<userDto> userDb = new ArrayList<>();

    public String save(userDto entity) {

        String userName = entity.getUserName();

        userDb.add(entity);

        System.out.println("{Current Table User]");
        System.out.println(userDb);
        return userName;

    }

    public userDto get(String key) {

        for (userDto dto : userDb) {

            if(dto.getId().equals(key)) {

                return dto;

            }
        }

        return null;

    }

    public void outDatabase() {

        System.out.println(userDb);

    }

}

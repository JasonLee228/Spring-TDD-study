package com.test.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class userDao {

//    private static Map<String, String> userDb = new HashMap<>();
    private static List<userDto> userDb = new ArrayList<>();

    public void outDatabase() { System.out.println("[Current user table]\n" + userDb); }

    public String save(userDto entity) {

        String userName = entity.getUserName();

        userDb.add(entity);

        outDatabase();
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

    public userDto update(userDto entity) {

        userDto findDto = null;
        int index = -1;

        for (int i=0;i<userDb.size();i++) {

            if(userDb.get(i).getId().equals(entity.getId())) {

                findDto = userDb.get(i);
                index = i;
                break;

            }
        }

        if(findDto == null) {

            throw new NoSuchElementException("not found user");

        } else {

            findDto.setUserName(entity.getUserName());
            findDto.setPhoneNumber(entity.getPhoneNumber());

            userDb.set(index, findDto);

        }

        return userDb.get(index);
    }

    public userDto delete(String userId) {

        userDto entity = get(userId);

        if(entity == null) {

            throw new NoSuchElementException("not found user");

        }

        userDto findDto = null;
        int index = -1;

        for (int i=0;i<userDb.size();i++) {

            if(userDb.get(i).getId().equals(entity.getId())) {

                index = i;
                break;

            }
        }

        userDb.remove(index);

        return entity;

    }

}

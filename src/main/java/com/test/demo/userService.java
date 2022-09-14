package com.test.demo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class userService {

    private final userDao userDao;

    public String findUser(String id) {

        return userDao.select(id);

    }

    public String join(userDto userInfo) {

        String key = userInfo.getId();

        String value = userInfo.getUserName();

        return userDao.insert(key, value);

    }

}

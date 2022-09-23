package com.test.demo;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class userService {

    private final userDao userDao;

    public String join(userDto userInfo) {

        log.info("Join request, user information : {}", userInfo);

        userDto existUser = userDao.get(userInfo.getId());

        if(existUser != null) {

            log.error("userId [{}} is already exist, Don't save !", userInfo.getId());

            return null;
        }

        return userDao.save(userInfo);

    }
    public userDto findUser(String id) {

        userDao.outDatabase();

        userDto result = userDao.get(id);

        log.info("Find request, find user information : {}", result);

        return result;

    }



}

package com.test.demo.auth;

import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionDao sessionDao;

    public boolean tokenValidate(String tokenValue) {
        Session session = sessionDao.findByAccessTokenValue(tokenValue);
        return (session == null || !session.validate()) ? false : true;
    }

}

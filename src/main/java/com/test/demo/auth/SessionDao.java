package com.test.demo.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class SessionDao {

    private static List<Session> sessions = Collections.synchronizedList(new ArrayList<Session>());

    public boolean save(Session session) {
        return sessions.add(session);
    }

    public Session find(Session session) {
        for (Session entity : sessions) {
            if (entity.getAccessToken().equals(session.getAccessToken())) {
                return entity;
            }
        }
        return null;
    }

    public Session findByAccessTokenValue(String tokenValue) {
        for (Session entity : sessions) {
            if (entity.getAccessToken().getValue().equals(tokenValue)) {
                return entity;
            }
        }
        return null;
    }

    public List<Session> findExpiredSessions() {

        List<Session> expiredSessions = new ArrayList<>();

        for (Session entity : sessions) {
            if (!entity.validate()) {
                expiredSessions.add(entity);
            }
        }

        return expiredSessions;
    }

}

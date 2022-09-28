package com.test.demo.auth;

import java.time.Instant;
import java.util.UUID;

public final class AccessToken {

    private String value;
    private long createTime = 0;
    private long expiryTime = 0;

    private AccessToken(final long expirationInterval) {
        createTime = Instant.now().toEpochMilli();
        expiryTime = createTime + expirationInterval;
        value = UUID.randomUUID().toString();
    }

    public static AccessToken create(long expirationInterval) {
        return new AccessToken(expirationInterval);
    }

    public boolean equals(AccessToken accessToken) {
        return this.value.equals(accessToken.getValue());
    }

    public boolean validate() {
        return Instant.now().toEpochMilli() < expiryTime;
    }

    public String getValue() {
        return value;
    }

}

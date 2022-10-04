package com.test.demo.auth;

import com.test.demo.dto.userDto;

import lombok.NonNull;

public class Session {

    @NonNull
    private AccessToken accessToken;
    @NonNull
    private userDto userDto;

    public Session(final userDto userDto) {
        this.accessToken = AccessToken.create(300000);
        this.userDto = userDto;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public userDto getUserDto() {
        return userDto;
    }

    public boolean validate() {
        return this.accessToken.validate();
    }

}

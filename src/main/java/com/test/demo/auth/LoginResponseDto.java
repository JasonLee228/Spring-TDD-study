package com.test.demo.auth;

import com.test.demo.dto.userDto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

    @NonNull
    private AccessToken accessToken;
    @NonNull
    private userDto userDto;

    public LoginResponseDto(AccessToken accessToken, com.test.demo.dto.userDto userDto) {
        this.accessToken = accessToken;
        this.userDto = userDto;
    }

}

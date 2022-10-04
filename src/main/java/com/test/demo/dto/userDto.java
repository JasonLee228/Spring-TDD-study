package com.test.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
//@Builder
public class userDto {

    @NonNull
    private String id;

    @NonNull
    private String password;

    @NonNull
    private String userName;

    private String phoneNumber;

    @Builder
    public userDto(@NonNull String id, @NonNull String password,
                   @NonNull String userName, String phoneNumber) {
        this.id = id;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }
}

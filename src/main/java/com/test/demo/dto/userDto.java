package com.test.demo.dto;

import lombok.*;
import org.springframework.lang.Nullable;

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

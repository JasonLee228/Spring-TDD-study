package com.test.demo;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
//@RequiredArgsConstructor
//@Builder
public class userDto {

    @NonNull
    private String id;

    @NonNull
    private String userName;

    @Builder
    public  userDto(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

}

package com.test.demo.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class userPatchDto {

    @NonNull
    private String id;

    @NonNull
    private String password;

    @NonNull
    private String userName;

    private String phoneNumber;

    @Builder

    public userPatchDto(@NonNull String id, @NonNull String password
            , @NonNull String userName, String phoneNumber) {

        this.id = id;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;

    }
    public static userDto convertDto(@NonNull userPatchDto req) {

        // userDto 의 @Builder 를 통해서 DTO 변환
        return userDto.builder()
                .id(req.getId())
                .password(req.getPassword())
                .userName(req.getUserName())
                .phoneNumber(req.getPhoneNumber())
                .build();

    }
}

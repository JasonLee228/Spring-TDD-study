package com.test.demo.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class userSaveDto {

    /*
    * 220924
    * TODO:
    *  우섭님이 말씀하신 내용처럼, 계층 및 사용 용도 별로 DTO 를 나누어주는 예시입니다.
    *  해당 DTO 는 유저의 회원가입 시에만 사용하는 별도 DTO 입니다.
    *  DTO 의 내용은 userDto 와 같습니다.
    *
    */

    @NonNull
    private String id;

    @NonNull
    private String password;

    @NonNull
    private String userName;

    private String phoneNumber;

    @Builder
    public userSaveDto(@NonNull String id, @NonNull String password,
                       @NonNull String userName, String phoneNumber) {
        this.id = id;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    // userSaveDto -> userDto 의 변환을 도와주는 메소드
    // static 으로 선언된 메소드는 객체를 생성하지 않아도(new 또는 @Builder 를 통해) 외부 사용이 가능
    public static userDto convertDto(@NonNull userSaveDto req) {

        // userDto 의 @Builder 를 통해서 DTO 변환
        return userDto.builder()
                .id(req.getId())
                .password(req.getPassword())
                .userName(req.getUserName())
                .phoneNumber(req.getPhoneNumber())
                .build();

    }
}

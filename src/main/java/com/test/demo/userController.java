package com.test.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class userController {

    private final userService userService;

    @PostMapping("/join")
    public String join(@RequestBody userDto requestBody) {

        log.info("[API CALL] : [POST] /join");
        log.info("[REQUEST] : {}", requestBody);

        String userId = userService.join(requestBody);

        if(userId != null) {

            return "User " + userId + "is Successful join our app";

        } else {

            return "join error";

        }

    }

    @GetMapping("/find")
    public userDto findUser(@RequestParam String id) {

        log.info("[API CALL] : [GET] /find");
        log.info("[REQUEST] : {}", id);

        userDto result = userService.findUser(id);

        if(result == null) {

            throw new NoSuchElementException();
        }

        return result;
    }









    // 간단히 컨트롤러의 호출 여부를 검사할 수 있는 테스트용 APi입니다.
    @GetMapping("/test")
    public ResponseEntity getTestUserName(@RequestParam String id) {

        if(id.equals("A")) {
            return new ResponseEntity<>("testName", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}

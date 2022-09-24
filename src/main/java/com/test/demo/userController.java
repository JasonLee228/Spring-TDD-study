package com.test.demo;

import com.test.demo.dto.userDto;
import com.test.demo.dto.userSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class userController {

    private final userService userService;

    @PostMapping("/join")
    public String join(@RequestBody userSaveDto requestBody) {

        log.info("[API CALL] : [POST] /join");
        log.info("[REQUEST] : {}", requestBody);

        // userService 의 join(회원가입) 메소드 실행
        String userId = userService.join(requestBody);

        // save() 의 반환형인 String userId가 null 이 아니라면 성공적으로 회원가입 완료
        if(userId != null) {

            return "User " + userId + "is Successful join our app";

        } else {

            // null 일 경우, 중복되는 아이디가 있어 회원가입이 정상적으로 이루어지지 않음
            return "join error";

        }

    }

    @PostMapping("/login")
    public userDto login(@RequestParam String userId, @RequestParam String password) {

        log.info("[API CALL] : [POST] /login");
        log.info("[REQUEST] : {}", userId + " : " + password);

        // userService 의 로그인 메소드 실행
        userDto result = userService.login(userId, password);

        return result;
    }

    @GetMapping("/find")
    public userDto findUser(@RequestParam String userId) {

        log.info("[API CALL] : [GET] /find");
        log.info("[REQUEST] : {}", userId);

        // userService 의 find 메소드 실행
        userDto result = userService.findUser(userId);

        return result;
    }

    @PatchMapping("/update")
    public userDto updateUser(@RequestBody userDto requestBody) {

        log.info("[API CALL] : [PATCH] /update");
        log.info("[REQUEST] : {}", requestBody);

        // userService 의 update 메소드 실행
        userDto result = userService.update(requestBody);

        return result;

    }

    @DeleteMapping("/delete")
    public userDto delete(@RequestParam String userId) {

        log.info("[API CALL] : [DELETE] /delete");
        log.info("[REQUEST] : {}", userId);

        // userService 의 delete 메소드 실행
        userDto result = userService.delete(userId);

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

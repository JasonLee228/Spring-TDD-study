package com.test.demo;


import com.test.demo.dto.userDto;
import com.test.demo.dto.userPatchDto;
import com.test.demo.dto.userSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class userService {

    // repository 로 선언된 userDao 사용을 위함
    private final userDao userDao;

    // 유저 회원가입 메소드
    public String join(userSaveDto req) {

        log.info("Join request, user information : {}", req);

        // 요청이 들어온 아이디 값으로 유저를 먼저 검색
        userDto existUser = userDao.get(req.getId());

        // 해당 아이디를 가진 유저가 이미 존재하기 때문에 회원가입을 진행할 수 없음
        if(existUser != null) {

            // 이미 존재하는 회원임을 알림
            log.error("userId [{}] is already exist, Don't save !", req.getId());

            return null;
        }

        // userSaveDto 의 static 메소드로 선언된 convert() 를 통해서 userSaveDto -> userDto(entity)
        userDto entity = userSaveDto.convertDto(req);

        // userDao 의 save 함수 실행
        return userDao.save(entity);

    }

    // 로그인
    public userDto login(String userId, String password) {

        // 유저 검증을 위해서 userDao.get 이용하여 유저 검색
        userDto user = userDao.get(userId);

        // 찾아낸 유저가 없다면
        if(user == null) {

            // 에러 반환
            throw new NoSuchElementException("not found user");

        }

        // 유저 검색에 성공, 비밀번호 검증을 위해서 userDb의 정보와 입력한 password 동일한 지 검증
        // 사실 user 가 이미 있기 때문에 굳이 따로 뺄 필요 없는 로직이기는 합니다 ㅎ
        if (userDao.validPassword(userId, password)) {

            // 검증에 성공, 로그인 되었다고 가정하고 로그인된 유저의 정보 반환
            return user;

        } else {

            // 검증에 실패, 에러 반환
            throw new InputMismatchException("Invalid password");

        }

    }

    // 유저 검색
    public userDto findUser(String id) {

        userDao.outDatabase();

        // userDao.get 이용하여 유저 검색
        userDto result = userDao.get(id);

        log.info("Find request, find user information : {}", result);

        // 검색 결과가 없을 시
        if(result == null) {

            // 에러 반환
            throw new NoSuchElementException("not found user");
        }

        // 검색에 성공하여 검색한 유저 정보 반환
        return result;

    }

    // 유저 정보 업데이트
    public userDto update(userPatchDto updateReq) {

        userDto entity = userPatchDto.convertDto(updateReq);

        // userDao.update
        userDto result = userDao.update(entity);

        // 업데이트 이후 userDb List 상태 표시
        userDao.outDatabase();

        // 업데이트 된 정보 반환
        return result;

    }

    public userDto delete(String userId) {

        // userDao.delete
        userDto result = userDao.delete(userId);

        // 유저 삭제 이후 userDb List 상태 표시
        userDao.outDatabase();

        // 삭제한 유저 정보 반환
        return result;

    }

}

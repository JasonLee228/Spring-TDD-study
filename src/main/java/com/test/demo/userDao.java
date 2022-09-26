package com.test.demo;

import com.test.demo.dto.userDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class userDao {

//    private static Map<String, String> userDb = new HashMap<>();

    // TODO: Map<> 으로 사용했던 userDb 를 DTO List 로 변환
    private static List<userDto> userDb = new ArrayList<>();

    // TODO: 현재 데이터베이스 테이블(userDb) 의 현 상태를 반환
    public void outDatabase() {

        System.out.println("[Current user table]");

        for(userDto dto : userDb) {

            System.out.println(dto);
        }
        System.out.println();

    }

    // 회원가입
    public String save(userDto entity) {

        // 반환 용도의 userName
        String userName = entity.getUserName();

        // List.add() 메소드를 이용
        // 새로운 유저 객체를 DB에 추가한다고 생각하면 됩니다.
        userDb.add(entity);

        // 유저 추가 이후 현재 DB 상태 표시
        outDatabase();
        return userName;

    }

    // 비밀번호 확인
    public boolean validPassword(String userId, String password) {

        // userId / password 받아서 해당하는 유저 아이디 / 비밀번호 검증
        if(get(userId).getPassword().equals(password)) {

            return true;

        } else {

            return false;

        }

    }

    /*
    * TODO
    *   Map 으로 이루어진 기존 상황에서는 Map.get(key) 를 이용하여 해당 사용자를 찾을 수 있었지만,
    *  List<DTO> 에서는 기존처럼 키를 통한 row 검색이 불가능합니다.
    *  때문에 List 를 순회하여 해당 key 를 가진 유저를 검색할 수 있도록 하였습니다.
    */
    public userDto get(String key) {

        // userDb 순회
        for (userDto dto : userDb) {

            // 입력된 키와 같은 userId를 가진 객체가 있다면,
            if(dto.getId().equals(key)) {

                // 해당 userDto 객체 반환
                return dto;

            }
        }

        // 전체를 순회했음에도 값이 없다면 null 반환(해당 아이디에 맞는 사용자가 존재하지 않음)
        return null;

    }

    /*
    * TODO:
    *  List.set() 을 사용하기 위해서 업데이트할 사용자의 id 로 검색을 실시합니다.
    *  검색을 통해 index 값을 찾아내고, 찾아낸 index 의 값을 List.set(index, updateValue)
    *  메소드로 update 를 수행합니다.
    *
    *  index 를 찾아내는 과정을 없애기 위해서는, Entity 로 사용되는 userDto 에 고유 id 값을
    *  추가하는 방법을 사용할 수 있습니다. 우리는 현재 List 로 DB 를 사용하고 있기 때문에
    *  List 의 순서 번호를 이에 이용할 수 있습니다.
    *
    *  하지만 어차피 DB를 붙이게 되면 사용 안할 로직이기 때문에,,, 아직은 별도 추가하지 않았습니다.
    */
    public userDto update(userDto entity) {

        int index = -1;

        userDto user = get(entity.getId());

        userDb.indexOf(user);

        for (int i=0;i<userDb.size();i++) {

            // index 를 검색하기 위해서 userDb 를 순회하여 해당 유저를 검색
            if(userDb.get(i).getId().equals(entity.getId())) {

                // 검색된다면 index 업데이트
                index = i;
                break;

            }
        }

        // 검색된 유저가 없을 경우 index 업데이트 되지 않으므로 -1
        if(index == -1) {

            // Exception 반환
            throw new NoSuchElementException("not found user");

        } else {

            // List.set 메소드 이용하여 해당 index 를 가진 유저 정보 업데이트
            userDb.set(index, entity);

        }

        // 업데이트 된 유저 정보 반환
        return userDb.get(index);
    }

    // TODO: 위 update 로직과 동일합니다.
    public userDto delete(String userId) {

        // userId 로 유저 검색
        userDto entity = get(userId);

        // 검색된 유저가 없을 시(id 가 잘못됨)
        if(entity == null) {

            // 에러 반환
            throw new NoSuchElementException("not found user");

        }

        int index = -1;

        // 순회를 돌며 index 검색
        for (int i=0;i<userDb.size();i++) {

            if(userDb.get(i).getId().equals(entity.getId())) {

                index = i;
                break;

            }
        }

        // 찾아낸 index 값을 통해 List.remove 메소드 이용하여 해당 정보 삭제
        userDb.remove(index);

        // 삭제된 유저 정보 반환
        return entity;

    }

}

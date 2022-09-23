package com.test.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
public class WebMvcTestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private userService userService; // mock이라 진짜 해줘야 하는지, 아니면 빈 올려버리는지.

    @Test
    @DisplayName("webMvcTest 테스트")
    public void webMvcTest() throws Exception {

        // given
        String reqId = "A";

        mockMvc.perform(get("/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", reqId))
                .andExpect(status().isOk())
                .andDo(print()); // response 확인

    }

    @Test
    @Order(1)
    @DisplayName("WebMvc를 통한 join 테스트")
    public void WebMvcJoin() throws Exception{

        // given
        String content = "{\"id\": \"a\", \"userName\": \"jason\"}";

//        given(userService.join(any(userDto.class))).willReturn("a");

        mockMvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andDo(print());


    }

    @Test
    @Order(2)
    @DisplayName("mockmvc find test")
    public void MockMvcTestFindUser() throws Exception {
//
//        // given
//        String id = "A";
//        given(userService.findUser(id)).willReturn("jason");
//
//        // when / then
//        mockMvc.perform(get("/find")
//                        .param("id", id))
//                .andExpect(status().isOk())
//                .andDo(print());

    }


}

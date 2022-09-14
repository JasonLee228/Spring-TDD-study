package com.test.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MockMvcControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Test
    @DisplayName("mockmvc example")
    public void MockMvcExample() throws Exception{

        // given
        String reqId = "A";

        mockMvc.perform(get("/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", reqId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(1)
    @DisplayName("mockmvc join test")
    public void MockMvcTestJoin() throws Exception {

        // given
        String content = "{\"id\": \"a\", \"userName\": \"jason\"}";

        // when
        mockMvc.perform(post("/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string("a"))
                .andDo(print());

    }

    @Test
    @Order(2)
    @DisplayName("mockmvc find test")
    public void MockMvcTestFindUser() throws Exception {

        // given
        String id = "a";
        String resultName = "jason";

        // when
        mockMvc.perform(get("/find")
                        .param("id", id))
                .andExpect(status().isOk())
                .andExpect(content().string(resultName))
                .andDo(print());

    }
}

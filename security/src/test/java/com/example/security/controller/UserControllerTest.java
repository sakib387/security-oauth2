package com.example.security.controller;

import com.example.security.model.User;
import com.example.security.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class UserControllerTest {

    private MockMvc mockMvc;
    private User user;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User(1L, "testuser", "password123", false);
    }

    @Test
    public void testFindAll() throws Exception {
        when(userService.findAll()).thenReturn(List.of(user));

        mockMvc.perform(get("/abc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username").value("testuser"));
        verify(userService, times(1)).findAll();
    }


    @Test
    public void testSaveUser() throws Exception {
        // when(userService.save(user)).thenReturn(user);
        when(userService.save(any(User.class))).thenReturn(user);
        String reqBody = new ObjectMapper().writeValueAsString(user);
        mockMvc.perform(post("/abc")

                        .contentType(MediaType.APPLICATION_JSON_VALUE)

                        .content(reqBody)

                )

                .andExpect(jsonPath("$.username").value("testuser"))

                .andDo(print())
                .andExpect(status().isOk());

    }


}
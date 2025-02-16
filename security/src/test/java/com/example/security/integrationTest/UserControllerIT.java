package com.example.security.integrationTest;

import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setup() {
        testUser = new User(null, "testuser", "password123", false);
        userRepository.deleteAll();
        userRepository.save(testUser);
    }

    @Test
    @WithMockUser(username = "sakib", roles = {"USER"})
    public void testGetUser() throws Exception {

        mockMvc.perform(get("/abc"))
                .andExpect(jsonPath("$.length()").value(1))  // Expect 2 users
                .andExpect(jsonPath("$[0].username").value("testuser"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "sakib", roles = {"USER"})
    public void testSaveUser() throws Exception {
        String reqBody = objectMapper.writeValueAsString(testUser);

        mockMvc.perform(post("/abc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andDo(print());
    }
}

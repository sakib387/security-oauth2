package com.example.security.service;

import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "testuser", "password123", false);
    }

    @Test
    public void testFindAll(){

        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<User> users = userService.findAll();

        assertEquals(1,userService.findAll().size());
    }

    @Test
    public void testSave(){
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals(user,savedUser);
    }

}
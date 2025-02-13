package com.example.security.repository;

import com.example.security.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Test
    void findByUsername() {
        //Arrange
        User user = new User(null,"testuser", "password123", false);
        userRepository.save(user);

        //Act
        User found=userRepository.findByUsername("testuser");

        //Assert
        assertNotNull(found);
        assertEquals("testuser",found.getUsername());
    }

}
package com.example.security.controller;


import com.example.security.model.User;
import com.example.security.service.OAuthService;
import com.example.security.service.UserService;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
 import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abc") // Correct way to define a base path
public class UserController {

    private final UserService userService;
    private final OAuth2AuthorizedClientService authorizedClientService;
    private final OAuthService oauthService;

    public UserController(UserService userService, OAuth2AuthorizedClientService authorizedClientService, OAuthService oauthService) {
        this.userService = userService;
        this.authorizedClientService = authorizedClientService;
        this.oauthService = oauthService;
    }

    @GetMapping
    public List<User> findAllUser() {
        return userService.findAll();
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }






}

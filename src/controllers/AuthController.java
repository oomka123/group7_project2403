package controllers;

import models.User;
import services.UserService;

public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    public String registerUser(String username, String password) {
        return this.userService.registerUser(username, password);
    }

    public User loginUser(String username, String password) {
        return userService.loginUser(username, password);
    }
}
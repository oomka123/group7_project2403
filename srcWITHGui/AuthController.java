package controllers;

import models.User;
import repositories.UserRepository;

public class AuthController {
    private UserRepository userRepo;

    public AuthController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public String registerUser(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return "Username and password cannot be empty.";
        }

        User user = new User(username, password);
        boolean success;
        try {
            success = this.userRepo.userRegistration(user);
        } catch (Exception e) {
            return "Error during registration: " + e.getMessage();
        }

        return success ? "Registration successful!" : "Registration failed. Username may already exist.";
    }

    public User loginUser(String username, String password) {
        User user = userRepo.userLogin(username, password);
        return user;
    }
}
package services;

import models.User;
import repositories.UserRepository;

public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public String registerUser(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return "Username cannot be empty.";
        }
        if (password == null || password.trim().isEmpty()) {
            return "Password cannot be empty.";
        }
        if (password.length() < 6) {
            return "Password must be at least 6 characters long.";
        }

        User user = new User(username.trim(), password);

        boolean success = userRepo.userRegistration(user);

        return success ? "Registration successful!" : "Registration failed. Username may already exist.";
    }


    public User loginUser(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username cannot be empty.");
            return null;
        }
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password cannot be empty.");
            return null;
        }

        User user = userRepo.userLogin(username, password);
        if (user == null) {
            System.out.println("Invalid username or password.");
        }

        return user;
    }

}

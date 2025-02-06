package controllers;

import enums.RoleCategory;
import controllers.Icontollers.IAuthController;
import models.User;
import services.UserService;

import java.util.List;

public class AuthController implements IAuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String registerUser(String username, String password) {
        return userService.registerUser(username, password);
    }

    @Override
    public User loginUser(String username, String password) {
        return userService.loginUser(username, password);
    }

    @Override
    public String getUserRole(int userId) {
        return userService.getUserRole(userId);
    }

    @Override
    public String updateUserRole(int adminId, int userId, RoleCategory newRole) {
        User admin = userService.getUserById(adminId);
        if (admin == null || admin.getRole() != RoleCategory.ADMIN) {
            return "Access denied! Only ADMIN can change roles.";
        }

        boolean updated = userService.updateUserRole(userId, newRole.name());
        return updated ? "User role updated successfully!" : "Failed to update role.";
    }

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public User getUserById(int userId) {
        return userService.getUserById(userId);
    }

    public String deleteUser(int userId) {
        boolean isDeleted = userService.deleteUser(userId);
        return isDeleted ? "User deleted successfully!" : "Failed to delete user.";
    }

}

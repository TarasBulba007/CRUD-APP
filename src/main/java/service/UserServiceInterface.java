package service;

import model.User;

import java.util.List;

public interface UserServiceInterface {
    void createUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
    void deleteAllUsers();
}

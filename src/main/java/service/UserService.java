package service;

import model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
    void deleteAllUsers();
    User findUser(String name, String password);
}

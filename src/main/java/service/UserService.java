package service;

import model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(Long id);
    void deleteAllUsers();
    User findUser(String name, String password);
    boolean validateUser(String login, String password);
    public User getUserByLogin(String login);
}

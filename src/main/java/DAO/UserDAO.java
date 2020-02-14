package DAO;

import model.User;
import model.User;

import java.util.Date;
import java.util.List;

public interface UserDAO {

    void createUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
    void updateUser(User user);

    void deleteUser(int id);
    void deleteAllUsers();

    User findUser(String userName, String password);
}


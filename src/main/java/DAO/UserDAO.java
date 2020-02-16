package DAO;

import model.User;
import model.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface UserDAO {

    void createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void updateUser(User user);

    void deleteUser(Long id);
    void deleteAllUsers();
    User findUser(String login, String password);
    boolean validateUser(String login, String password) throws SQLException;
    User getUserByLogin(String login);
}


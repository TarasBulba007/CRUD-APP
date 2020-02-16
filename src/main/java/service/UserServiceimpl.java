package service;


import DAO.*;
import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userService;

    public static UserServiceImpl getUserService(){
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    ResourceBundle prop = ResourceBundle.getBundle("property");
   private String property = prop.getString("connection.type");

    private UserDAO dao = UserDaoFactoryIml.createDAO(property);

    @Override
    public void createUser(User user) {
        dao.createUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return dao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        dao.deleteUser(id);
    }

    @Override
    public void deleteAllUsers() {
        dao.deleteAllUsers();
    }

    @Override
    public User findUser(String name, String password) {
        return dao.findUser(name, password);
    }

    @Override
    public boolean validateUser(String login, String password)  {
        try {
            if (dao.validateUser(login, password)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        user = dao.getUserByLogin(login);
        return user;
    }
}

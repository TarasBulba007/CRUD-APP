package service;


import DAO.*;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;
import java.util.Properties;

public class UserService implements UserServiceInterface {

    private static UserService userService;

    public static UserService getUserService(){
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    Properties prop = new Properties();
    String property = prop.getProperty("key");
    java.util.logging.Logger.

    UserDaoFactory daos =  createDAO(property);
    UserDAO dao = daos.createDAO();

    @Override
    public void createUser(User user) {
        dao.createUser(user);
    }

    @Override
    public User getUserById(int id) {
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
    public void deleteUser(int id) {
        dao.deleteUser(id);
    }

    @Override
    public void deleteAllUsers() {
        dao.deleteAllUsers();
    }

    static UserDaoFactory createDAO(String property) {
        if (property.equalsIgnoreCase("hibernate")) {
            return new UserHibernateDaoFactory();
        } else if (property.equalsIgnoreCase("jdbc")) {
            return new UserJdbcDaoFactory();
        } else {
            throw new RuntimeException("unknown factory");
        }
    }
}

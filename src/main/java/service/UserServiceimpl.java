package service;


import DAO.*;
import model.User;

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


    private UserDaoFactory daos =  createDaoFactory(property);
    private UserDAO dao = daos.createDAO();

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

    private static UserDaoFactory createDaoFactory(String property) {
        System.out.println(property);
        if (property.equalsIgnoreCase("hibernate")) {
            return new UserHibernateDaoFactory();
        } else if (property.equalsIgnoreCase("jdbc")) {
            return new UserJdbcDaoFactory();
        } else {
            throw new RuntimeException("unknown factory");
        }
    }
}

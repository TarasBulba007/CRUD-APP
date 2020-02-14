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

    private UserDAO dao = UserDaoFactoryIml.createDao(property);

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

    @Override
    public User findUser(String name, String password) {
        return dao.findUser(name, password);
    }


}

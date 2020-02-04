package service;


import DAO.UserDAO;
import model.User;
import org.hibernate.SessionFactory;
import DAO.UserHibernateDAO;
import util.DBHelper;

import java.util.List;

public class UserService implements UserServiceInterface {

    private static UserService userService;

    public static UserService getUserService(){
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    UserDAO dao = UserHibernateDAO.getHibernateDAO();

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
}

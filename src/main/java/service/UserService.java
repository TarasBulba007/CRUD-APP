package service;


import model.User;
import org.hibernate.SessionFactory;
import DAO.UserHibernateDAO;
import util.DBHelper;

import java.util.List;

public class UserService  {

    private static UserService userService;

    private SessionFactory sessionFactory;

    public UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getUserService(){
        if (userService == null) {
            userService = new UserService(DBHelper.getSessionFactory());
        }
        return userService;
    }

    public void createUser(User user) {
       UserHibernateDAO dao = new UserHibernateDAO(sessionFactory.getCurrentSession());
        dao.createUser(user);
    }

    public User getUserById(int id) {
        UserHibernateDAO dao = new UserHibernateDAO(sessionFactory.getCurrentSession());
        return dao.getUserById(id);
    }

    public List<User> getAllUsers() {
        UserHibernateDAO dao = new UserHibernateDAO(sessionFactory.getCurrentSession());
        return dao.getAllUsers();
    }

    public void updateUser(User user) {
        UserHibernateDAO dao = new UserHibernateDAO(sessionFactory.getCurrentSession());
        dao.updateUser(user);
    }

    public void deleteUser(int id) {
        UserHibernateDAO dao = new UserHibernateDAO(sessionFactory.openSession());
        dao.deleteUser(id);
    }

    public void deleteAllUsers() {
        UserHibernateDAO dao = new UserHibernateDAO(sessionFactory.getCurrentSession());
        dao.deleteAllUsers();
    }


}

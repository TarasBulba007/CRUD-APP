package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.DBHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private static UserHibernateDAO hibernateDAO;
    private SessionFactory sessionFactory;

    public UserHibernateDAO() {
        this.sessionFactory = DBHelper.getSessionFactory();
    }

    public static UserHibernateDAO getHibernateDAO() {
        if (hibernateDAO == null) {
            hibernateDAO = new UserHibernateDAO();
        }
        return hibernateDAO;
    }

    @Override
    public void createUser(User user) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("SELECT i from " + User.class.getName() + " i WHERE i.id=:id");
            query.setParameter("id", id);
            user = (User) query.uniqueResult();
            //    session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            // CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
            // criteriaQuery.from(User.class);
            // users = session.createQuery(criteriaQuery).getResultList();
            users = session.createQuery("from " + User.class.getName()).list();
//            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            //  User user = session.get(User.class, id);
            //  session.delete(user);
            Query query = session.createQuery("DELETE FROM " + User.class.getName() + " i WHERE i.id=:id");
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (SessionException e) {

        }
    }

    @Override
    public void deleteAllUsers() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM " + User.class.getName()).executeUpdate();
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUser(String userName, String password) {
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("SELECT i from " + User.class.getName() + " i WHERE i.name=:name");
            query.setParameter("name", userName);
            user = (User) query.uniqueResult();
        } catch (SessionException e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public boolean validateUser(String login, String password) throws SQLException {
        User user = getUserByLogin(login);
        if (user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("SELECT i from " + User.class.getName() + " i WHERE i.login=:login");
            query.setParameter("login", login);
            user = (User) query.uniqueResult();
            //    session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }
        return user;
    }
}


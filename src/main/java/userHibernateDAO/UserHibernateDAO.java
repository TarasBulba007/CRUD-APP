package userHibernateDAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionException;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class UserHibernateDAO  {

    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    public void createUser(User user) {
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public User getUserById(int id) {
        User user = null;
        try {
            session.beginTransaction();
            user = session.get(User.class, id);
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            session.beginTransaction();
           CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
           criteriaQuery.from(User.class);
           users = session.createQuery(criteriaQuery).getResultList();
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }


    public void updateUser(User user) {
        try {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteUser(int id) {
        try {
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch(SessionException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteAllUsers() {
        try {
            session.beginTransaction();
            session.createQuery("DELETE From User").executeUpdate();
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

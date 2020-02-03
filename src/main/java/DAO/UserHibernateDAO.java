package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.query.Query;

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
            //user = session.get(User.class, id);
            Query query = session.createQuery("SELECT i from " + User.class.getName() + " i " + " WHERE i.id=:id");
            query.setParameter("id", id);
            user = (User) query.uniqueResult();
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
          // CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
          // criteriaQuery.from(User.class);
          // users = session.createQuery(criteriaQuery).getResultList();
             users = session.createQuery("from " + User.class.getName()).list();
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
          //  User user = session.get(User.class, id);
          //  session.delete(user);
            Query query = session.createQuery("DELETE FROM " + User.class.getName() + " i " + "WHERE i.id=:id");
            query.setParameter("id", id);
            query.executeUpdate();
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
            session.createQuery("DELETE FROM " + User.class.getName()).executeUpdate();
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

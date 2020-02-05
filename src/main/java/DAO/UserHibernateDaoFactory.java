package DAO;

public class UserHibernateDaoFactory implements UserDaoFactory {
    @Override
    public UserDAO createDAO() {
        return new UserHibernateDAO();
    }
}

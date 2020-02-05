package DAO;

public class UserJdbcDaoFactory implements UserDaoFactory {
    @Override
    public UserDAO createDAO() {
        return new UserJdbcDAO();
    }
}

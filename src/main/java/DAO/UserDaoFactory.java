package DAO;

public interface UserDaoFactory {
    UserDAO createDAO(String property);
}

package DAO;

public class UserDaoFactoryIml {

    public static UserDAO createDAO(String property) {
        System.out.println(property);
        if (property.equalsIgnoreCase("hibernate")) {
            return new UserHibernateDAO();
        } else if (property.equalsIgnoreCase("jdbc")) {
            return new UserJdbcDAO();
        } else {
            throw new RuntimeException("unknown factory");
        }
    }

}

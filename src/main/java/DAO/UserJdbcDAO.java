package DAO;

import DAO.UserDAO;
import model.User;
import model.User;
import util.DBHelper;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    private static Connection connection;
    UserJdbcDAO() {
        connection = DBHelper.getConnection();
    }

    @Override
    public void createUser(User user)  {
        try (
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(login, password, role, name, email, phoneNumber, birthDate) values(?,?,?,?,?,?,?) ")) {
            System.out.println("Create user: " +  user.getLogin() + " " + user.getName() + " " +" " + user.getEmail() + " " + user.getPhoneNumber());
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            if (user.getLogin().equalsIgnoreCase("admin")) {
                preparedStatement.setString(3, "admin");
            } else {
                preparedStatement.setString(3, "user");
            }
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.setDate(7, java.sql.Date.valueOf(user.getBirthDate()));
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, login, password, role, name, email, phoneNumber, birthDate FROM users WHERE id =?");) {
            preparedStatement.setLong(1, id);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                String login = res.getNString("login");
                String password = res.getNString("password");
                String role = res.getNString("role");
                String name = res.getString("name");
                String email = res.getString("email");
                String phoneNumber = res.getString("phoneNumber");
                LocalDate birthDate = res.getDate("birthDate").toLocalDate();
                user = new User(id, login, password, role, name, email, phoneNumber, birthDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findUser(String userName, String password) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, login, password, role, name, email, phoneNumber, birthDate FROM users WHERE name =?");){
            preparedStatement.setNString(1, userName);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                if (res.getNString("password").equals(password)) {
                    String login = res.getNString("login");
                    String role = res.getNString("role");
                    String email = res.getString("email");
                    String phoneNumber = res.getString("phoneNumber");
                    LocalDate birthDate = res.getDate("birthDate").toLocalDate();
                    user = new User(login, password, role, userName, email, phoneNumber, birthDate);
                }
                else {
                    return null;
                }
            }
        }catch (SQLException e) {
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
    public User getUserByLogin(String login)  {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, password, role, name, email, phoneNumber, birthDate FROM users WHERE login =?");){
        preparedStatement.setString(1, login);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                    String password = res.getNString("password");
                    String name = res.getNString("name");
                    String role = res.getNString("role");
                    String email = res.getString("email");
                    String phoneNumber = res.getString("phoneNumber");
                    LocalDate birthDate = res.getDate("birthDate").toLocalDate();
                    user = new User(login, password, role, name, email, phoneNumber, birthDate);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM users");) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String login = rs.getString("login");
                String password = rs.getNString("password");
                String role = rs.getNString("role");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                LocalDate birthDate = rs.getDate("birthDate").toLocalDate();
                users.add(new User( id, login, password, role, name, email,phoneNumber, birthDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void updateUser(User user)  {
      //  boolean updated = false;
        try (PreparedStatement statement = connection.prepareStatement("UPDATE users SET login=?, password=?, name=?, email=?, phoneNumber=?, birthDate=? WHERE id=?");) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());

            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhoneNumber());
            statement.setDate(6, java.sql.Date.valueOf(user.getBirthDate()));
            statement.setLong(7, user.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
      //  return updated;
    }

    /**
     * @Override public void updateUserBirthDate(User user, String password, Date date) {
     * if (validateUser(user.getLogin(), password)) {
     * try {
     * PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login=?");
     * statement.setString(1, user.getLogin());
     * ResultSet res = statement.executeQuery();
     * res.next();
     * statement = connection.prepareStatement("UPDATE users SET birthDate=? WHERE login=?");
     * statement.setDate(1, (java.sql.Date) date);
     * statement.setNString(2, user.getLogin());
     * statement.executeUpdate();
     * res.close();
     * statement.close();
     * } catch (SQLException e) {
     * e.printStackTrace();
     * }
     * }
     * }
     * @param id
     */

    @Override
    public void deleteUser(Long id)  {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?");) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllUsers() {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createTableUsers() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE users (id  LONG NOT NULL AUTO_INCREMENT, login varchar(255) NOT NULL, password varchar(255) NOT NULL , role varchar(60) NOT NULL , name varchar(255) NOT NULL, email varchar(220) NOT NULL, phoneNumber varchar(20), birthDate DATE NOT NULL , PRIMARY KEY (id));");
        statement.close();
    }

    public void dropTableUsers() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS users");
        statement.close();
    }
}

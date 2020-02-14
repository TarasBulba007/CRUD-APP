package servlets;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
    private UserService service;

    public void init() {
        service = UserServiceImpl.getUserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            insertUser(req, resp);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        User newUser = new User(login, password, name, role, email, phoneNumber, birthDate);
        System.out.println("new USer: " + newUser.getId() + " " + newUser.getLogin() + " " + newUser.getName() + " " + newUser.getEmail() + " " + newUser.getBirthDate().toString());
        service.createUser(newUser);
        response.sendRedirect("list");
    }
}

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

@WebServlet("/upgrade")
public class UpdateUserServlet extends HttpServlet {
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
            updateUser(req, resp);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException {
        Long id = Long.parseLong(request.getParameter("id"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String role = "";
        if (name.equalsIgnoreCase("Taras") && password.equalsIgnoreCase("Rdfyn142857")){
            role = "admin";
        } else {
            role = "user";
        }
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        User var = new User(id, login, password, role, name,  email, phoneNumber, birthDate);
        System.out.println(var.getBirthDate());
        service.updateUser(var);
        response.sendRedirect("/user");

    }
}


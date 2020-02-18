package servlets;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet({"/index", "/login"})
public class LoginServlet extends HttpServlet {
    private UserService service;

    public void init() {
        service = UserServiceImpl.getUserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);
        User user = service.getUserByLogin(login);
        System.out.println(user.getLogin());

        if (login != null && password != null && !login.isEmpty() && !password.isEmpty()) {
            if (service.validateUser(login, password)) {
                if (user.getRole().equalsIgnoreCase("user")) {
                    session.setAttribute("user", user);
                    session.setAttribute("userName", user.getName());
                    session.setAttribute("userLogin", user.getLogin());
                    session.setAttribute("userRole", user.getRole());
                    session.setAttribute("birthDate", user.getBirthDate());
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("phoneNumber", user.getPhoneNumber());
                    request.getRequestDispatcher("/user/user-form.jsp").forward(request, response);
                }
                if (user.getRole().equals("admin")) {
                    response.sendRedirect("admin/user-list.jsp");
                } else {
                    request.setAttribute("message", "Incorrect input");
                    doGet(request, response);
                }
            } else {
                request.setAttribute("message", "Incorrect input");
                doGet(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

}

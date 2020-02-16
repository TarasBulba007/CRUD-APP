package servlets;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/index")
public class LoginServlet extends HttpServlet {
    private UserService service;

    public void init() {
        service = UserServiceImpl.getUserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        User user = service.getUserByLogin(login);

        if (service.validateUser(login, password)) {
            if (user.getRole().equals("user")) {
                session.setAttribute("login", login);
                session.setAttribute("role", user.getRole());
                session.setAttribute("name", user.getName());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("phoneNumber", user.getPhoneNumber());
                session.setAttribute("birthDate", user.getBirthDate());
                response.sendRedirect("user/userInfoView.jsp");
            }
            if (user.getRole().equals("admin")) {
                response.sendRedirect("webapp/admin/user-list.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/InsertServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

}

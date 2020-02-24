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
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet("/index")
public class LoginServlet extends HttpServlet {
    private UserService service;

    @Override
    public void init() {
        service = UserServiceImpl.getUserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        try {
            checkLogin(request, response);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = service.getUserByLogin(login);

        if (user != null && service.validateUser(user.getLogin(), password)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                session.setAttribute("id", user.getId());
                session.setAttribute("userRole", user.getRole());
                resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("/index.jsp");
        }
    }

}

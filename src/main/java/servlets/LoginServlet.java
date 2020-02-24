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
            HttpSession session = request.getSession();
            String role = (String) session.getAttribute("userRole");

            if (role.equalsIgnoreCase("admin")){
                response.sendRedirect("/admin/list");
            } else if (role.equalsIgnoreCase("user")){
                response.sendRedirect("/user");
            } else {
                session.invalidate();
                response.sendRedirect("/accessDeniedView.jsp");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        HttpSession session =  request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = service.getUserByLogin(login);

        if (user != null && service.validateUser(user.getLogin(), password)) {
                session.setAttribute("user", user);
                session.setAttribute("id", user.getId());
                session.setAttribute("userRole", user.getRole());

        } else {
            response.sendRedirect("/index.jsp");
        }
    }

}

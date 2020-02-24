package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    //private static final long  serialVersionUID = 1;

    public LogoutServlet() {
            }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().invalidate();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/logout.jsp");
        dispatcher.forward(req, resp);
       //final HttpSession session = req.getSession();
       //session.removeAttribute("user");
       //session.removeAttribute("name");
       //session.removeAttribute("password");
       //session.removeAttribute("userLogin");
       //session.removeAttribute("userRole");
       //req.getRequestDispatcher("/index.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

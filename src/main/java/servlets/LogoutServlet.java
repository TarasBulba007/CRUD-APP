package servlets;

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

    public LogoutServlet() {
        super();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session=req.getSession(true);
        Enumeration attributeNames=session.getAttributeNames(); //get all attributeNames associated with session

        while(attributeNames.hasMoreElements()) //destroy session
        {
            String name=(String)attributeNames.nextElement();
            String value=session.getAttribute(name).toString();
            session.removeAttribute(name);

            System.out.println(name+"="+value+"cleared");
        }
        resp.sendRedirect(req.getContextPath() + "/index");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

package servlets;

import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/deleteAll")
public class DeleteAllServlet extends HttpServlet {
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
            deleteAllUsers(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void deleteAllUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        service.deleteAllUsers();
        response.sendRedirect("list");
    }
}

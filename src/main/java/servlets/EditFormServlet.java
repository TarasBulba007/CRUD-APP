package servlets;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet({"/user/edit"})
public class EditFormServlet extends HttpServlet {
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
            showEditForm(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        User existingUser = service.getUserById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }
}


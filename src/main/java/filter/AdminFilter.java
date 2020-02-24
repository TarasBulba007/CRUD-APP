package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/list")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        User user = null;

        if (nonNull(session) && nonNull(user = (User) session.getAttribute("user"))) {
            String role = user.getRole();
            if (role.equals("admin")) {
                filterChain.doFilter(req, resp);
            } else {
                resp.sendRedirect("/user");
            }
        } else {
            resp.sendRedirect("/accessDeniedView.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}

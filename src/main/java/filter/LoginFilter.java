package filter;


import service.UserService;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/login", "/"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        String role = (String) session.getAttribute("userRole");

        if (role.equalsIgnoreCase("admin")){
            resp.sendRedirect("/list");
        } else if (role.equalsIgnoreCase("user")){
            resp.sendRedirect("/user");
        } else {
            session.invalidate();
            resp.sendRedirect("/index");
        }

    }

    @Override
    public void destroy() {

    }
}

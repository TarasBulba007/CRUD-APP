package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/admin", "/user"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("user");
        String loginURI = request.getContextPath() + "/index";
        boolean loggedIn = session != null && session.getAttribute("user") != null && session.getAttribute("userRole") != null;
        if (loggedIn) {
            System.out.println(user.getLogin());
            filterChain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/login").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
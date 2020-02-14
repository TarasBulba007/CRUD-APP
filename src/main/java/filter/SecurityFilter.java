package filter;

import model.User;
import request.UserRoleRequestWrapper;
import util.AppUtils;
import util.SecurityUtil;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    public SecurityFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

        User loginedUser = AppUtils.getLoginedUser(request.getSession());

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {

            String userName = loginedUser.getName();

            String role = loginedUser.getRole();


            wrapRequest = new UserRoleRequestWrapper(userName, role, request);
        }

        if (SecurityUtil.isSecurityPage(request)) {

            if (loginedUser == null) {

                String requestUri = request.getRequestURI();
                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);
                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }

            boolean hasPermission = SecurityUtil.hasPermission(wrapRequest);
            if (!hasPermission) {
                RequestDispatcher dispatcher //
                        = request.getServletContext().getRequestDispatcher("/accessDeniedView.jsp");

                dispatcher.forward(request, response);
                return;
            }
        }

        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}

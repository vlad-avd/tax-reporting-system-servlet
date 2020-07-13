package ua.kpi.controller.filter;

import ua.kpi.model.enums.Role;
import ua.kpi.util.PermissionChecker;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        PermissionChecker permissionChecker = new PermissionChecker();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Role role = Role.ROLE_GUEST;

        if(request.getSession().getAttribute("role") != null){
            role = Role.valueOf(request.getSession().getAttribute("role").toString());
        }

        if(permissionChecker.checkPermission(request, role)){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    public void destroy() {

    }
}

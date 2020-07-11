package ua.kpi.controller.filter;

import ua.kpi.model.enums.Role;
import ua.kpi.util.PermissionChecker;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static ua.kpi.constant.Pages.*;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        PermissionChecker permissionChecker = new PermissionChecker();
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        Role role = Role.ROLE_GUEST;

        if(request.getSession().getAttribute("role") != null){
            role = Role.valueOf(request.getSession().getAttribute("role").toString());
        }

        if(permissionChecker.checkPermission(request, role)){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            request.getRequestDispatcher(ROOT_FOLDER + TEMPLATE_PAGES + ERROR_403).forward(request, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}

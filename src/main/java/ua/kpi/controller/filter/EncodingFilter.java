package ua.kpi.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletResponse.setContentType("text/html");
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletRequest.setAttribute("locale",
                ((HttpServletRequest) servletRequest).getSession().getAttribute("locale"));
        filterChain.doFilter(servletRequest, servletResponse);

    }

    public void destroy() {

    }
}

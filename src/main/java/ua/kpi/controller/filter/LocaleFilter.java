package ua.kpi.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        String locale = servletRequest.getParameter("lang");

        if(locale == null) {
            locale = (String)session.getAttribute("locale");
        }

        session.setAttribute("locale", locale);
        Locale.setDefault(new Locale(locale));
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

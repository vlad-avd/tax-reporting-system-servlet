package ua.kpi.controller.action.guest;

import ua.kpi.controller.action.Action;
import ua.kpi.controller.action.MultipleRequest;
import ua.kpi.model.entity.User;
import ua.kpi.service.GuestService;
import ua.kpi.service.GuestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.kpi.constant.Pages.*;

public class Login extends MultipleRequest {

    private final GuestService guestService = new GuestServiceImpl();

    @Override
    protected String handleGetRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session != null){
            session.removeAttribute("username");
            session.removeAttribute("role");
        }
        return ROOT_FOLDER + GUEST_PAGES + LOGIN;
    }

    @Override
    protected String handlePostRequest(HttpServletRequest request) {
        String username = request.getParameter("username");
        User user = guestService.getUserByUsername(username);
        System.out.println(user);
        if(user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("user_id", user.getId());
            session.setAttribute("role", user.getRole().toString());
            return ROOT_FOLDER + HOME;
        }
        return ROOT_FOLDER + GUEST_PAGES + LOGIN;
    }
}

package ua.kpi.controller.action.guest;

import ua.kpi.controller.action.MultipleRequest;
import ua.kpi.model.entity.User;
import ua.kpi.service.GuestService;
import ua.kpi.service.impl.GuestServiceImpl;

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
        String password = request.getParameter("password");

        if(guestService.areUsernameAndPasswordCorrect(username, password)) {
            User user = guestService.getUserByUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getId());
            session.setAttribute("role", user.getRole().toString());
            return ROOT_FOLDER + HOME;
        }
        else {
            request.setAttribute("wrongUsernameOrPassword", "true");
        }

        return ROOT_FOLDER + GUEST_PAGES + LOGIN;
    }
}

package ua.kpi.controller.action.guest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.controller.action.MultipleRequest;
import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.model.entity.User;
import ua.kpi.service.GuestService;
import ua.kpi.service.impl.GuestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.kpi.constant.Pages.*;

public class Login extends MultipleRequest {

    private final GuestService guestService = new GuestServiceImpl();
    Logger logger = LoggerFactory.getLogger(Login.class);

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
    protected String handlePostRequest(HttpServletRequest request) throws UserNotFoundException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(guestService.areUsernameAndPasswordCorrect(username, password)) {
            User user = guestService.getUserByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException("User: " + username + " was not found."));
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getId());
            session.setAttribute("role", user.getRole().toString());
            logger.debug("User: " + username + "logged in.");
            return REDIRECT + HOME_PATH;
        } else {
            request.setAttribute("wrongUsernameOrPassword", "true");
            return ROOT_FOLDER + GUEST_PAGES + LOGIN;
        }
    }
}

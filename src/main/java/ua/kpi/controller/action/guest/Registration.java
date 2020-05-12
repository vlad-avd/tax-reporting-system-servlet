package ua.kpi.controller.action.guest;

import ua.kpi.controller.action.Action;
import ua.kpi.controller.action.MultipleRequest;
import ua.kpi.db.ConnectionPool;
import ua.kpi.db.PGConnectionPool;
import ua.kpi.dto.UserDto;
import ua.kpi.model.enums.Role;
import ua.kpi.service.GuestService;
import ua.kpi.service.GuestServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static ua.kpi.constant.Pages.*;

public class Registration extends MultipleRequest {

    private final GuestService guestService = new GuestServiceImpl();

    @Override
    protected String handleGetRequest(HttpServletRequest request) {
        return ROOT_FOLDER + GUEST_PAGES + REGISTRATION;
    }

    @Override
    protected String handlePostRequest(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDto user = UserDto.newBuilder().setUsername(username).setPassword(password).setRole(Role.ROLE_USER).build();
        guestService.createUser(user);
        return REDIRECT + LOGIN_PATH;
    }
}

package ua.kpi.controller.action.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.controller.action.Action;
import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.dto.PageableUserDto;
import ua.kpi.model.entity.User;
import ua.kpi.service.AdminService;
import ua.kpi.service.UserService;
import ua.kpi.service.impl.AdminServiceImpl;
import ua.kpi.service.impl.UserServiceImpl;
import ua.kpi.util.Page;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.*;

public class UserAction implements Action {

    AdminService adminService = new AdminServiceImpl();
    UserService userService = new UserServiceImpl();
    Logger logger = LoggerFactory.getLogger(UserAction.class);

    @Override
    public String handleRequest(HttpServletRequest request) throws UserNotFoundException {
        String userId = request.getParameter("id");

        if(userId != null) {
            Long id = Long.parseLong(userId);
            User user = userService.getUserById(id);
            request.setAttribute("user", user);
            return ROOT_FOLDER + USER_PAGES + REPORT;
        }

        Page page = new Page();

        if(request.getParameter("currentPage") != null) {
            page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
        }

        PageableUserDto pageableUserDto = adminService.getAllUsers(page);
        logger.debug("All users were received by admin.");
        page.setSize(pageableUserDto.getRowNumber());
        page.setPageNumber((int)Math.ceil((double)page.getSize() / page.getRecordsPerPage()));

        request.setAttribute("users", pageableUserDto.getUsers());
        request.setAttribute("noOfPages", page.getPageNumber());
        request.setAttribute("currentPage", page.getCurrentPage());
        request.setAttribute("recordsPerPage", page.getRecordsPerPage());

        return ROOT_FOLDER + ADMIN_PAGES + USER_LIST;
    }
}

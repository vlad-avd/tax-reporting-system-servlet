package ua.kpi.service;

import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;

public interface GuestService {

    User getUserByUsername(String username) throws UserNotFoundException;

    boolean createUser(UserDto user);

    boolean isUserExistsWithUsername(String username);

    boolean areUsernameAndPasswordCorrect(String username, String password);
}

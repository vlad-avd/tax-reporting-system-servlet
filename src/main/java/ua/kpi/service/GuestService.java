package ua.kpi.service;

import ua.kpi.dto.UserDto;
import ua.kpi.dto.UserValidationDto;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.Role;

public interface GuestService {

    User getUserByUsername(String username);

    UserValidationDto createUser(UserDto user);

    boolean isUserExistsWithUsername(String username);

    boolean areUsernameAndPasswordCorrect(String username, String password);
}

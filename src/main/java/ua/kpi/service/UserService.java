package ua.kpi.service;

import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;

public interface UserService {
    boolean updateUser(UserDto userDto);

    User getUserById(Long id) throws UserNotFoundException;
}

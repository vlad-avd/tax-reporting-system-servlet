package ua.kpi.service;

import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;

import java.util.Optional;

public interface UserService {
    boolean updateUser(UserDto userDto);

    Optional<User> getUserById(Long id) throws UserNotFoundException;
}

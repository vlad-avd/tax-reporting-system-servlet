package ua.kpi.dao;

import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;

import java.util.List;

public interface UserDao {
    User getUserByUsername(String username) throws UserNotFoundException;

    User getUserById(Long id) throws UserNotFoundException;

    boolean updateUser(UserDto userDto);

    boolean areUsernameAndPasswordCorrect(String username, String password);
}

package ua.kpi.service;

import ua.kpi.dto.UserDto;
import ua.kpi.dto.UserValidationDto;
import ua.kpi.model.entity.User;

public interface UserService {
    UserValidationDto updateUser(UserDto userDto);

    User getUserById(Long id);
}

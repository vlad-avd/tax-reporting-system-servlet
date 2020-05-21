package ua.kpi.dao;

import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.Role;

import java.util.List;

public interface UserDao {
    User getUserByUsername(String username);

    User getUserById(Long id);

    boolean createUser(UserDto userDTO);

    void setUserRole(Long id, Role role);

    List<User> getAllUsers();

    boolean updateUser(UserDto userDto);
}

package ua.kpi.dao;

import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.Role;

public interface UserDao {
    User getUserByUsername(String username);

    boolean createUser(UserDto userDTO);

    void setUserRole(Long id, Role role);
}

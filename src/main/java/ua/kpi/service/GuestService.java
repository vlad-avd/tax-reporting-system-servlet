package ua.kpi.service;

import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.Role;

public interface GuestService {

    User getUserByUsername(String username);

    boolean createUser(UserDto user);

    void setUserRole(Long id, Role role);
}

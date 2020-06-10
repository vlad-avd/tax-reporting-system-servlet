package ua.kpi.service;

import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.Role;

public interface GuestService {

    User getUserByUsername(String username);

    boolean createUser(UserDto user);

    void setUserRole(Long id, Role role);

    boolean isUserExistsWithUsername(String username);

    boolean areUsernameAndPasswordCorrect(String username, String password);
}

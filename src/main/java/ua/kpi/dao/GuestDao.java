package ua.kpi.dao;

import ua.kpi.dto.UserDto;

public interface GuestDao {
    boolean createUser(UserDto userDTO);

    boolean isUserExistsWithUsername(String username);
}

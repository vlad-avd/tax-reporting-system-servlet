package ua.kpi.dao;

import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;

import java.util.List;

public interface UserDao {
    User getUserByUsername(String username);

    User getUserById(Long id);

    boolean createUser(UserDto userDTO);

    List<User> getAllUsers(int currentPage, int recordsPerPage);

    boolean updateUser(UserDto userDto);

    int getUsersNumber();

    boolean isUserExistsWithUsername(String username);

    boolean areUsernameAndPasswordCorrect(String username, String password);
}

package ua.kpi.service.impl;

import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.dao.UserDao;
import ua.kpi.dao.impl.UserDaoImpl;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;
import ua.kpi.service.UserService;

import java.util.Optional;

/**
 * Class for receiving and processing data necessary for the user.
 * @author Vladyslav Avdiienko
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    UserDao userDao;

    {
        userDao = new UserDaoImpl();
    }

    /** Return user with given id.
     * @param id
     * @exception UserNotFoundException if user was not found.
     * @return User.
     * @see User
     */
    @Override
    public Optional<User> getUserById(Long id) throws UserNotFoundException {
        return Optional.ofNullable(userDao.getUserById(id));
    }

    /** Update user data.
     * @param user User to update.
     * @see UserDto
     * @return Updating result.
     */
    @Override
    public boolean updateUser(UserDto user) {
        return userDao.updateUser(user);
    }
}

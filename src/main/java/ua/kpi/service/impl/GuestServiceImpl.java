package ua.kpi.service.impl;

import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.dao.GuestDao;
import ua.kpi.dao.UserDao;
import ua.kpi.dao.impl.GuestDaoImpl;
import ua.kpi.dao.impl.UserDaoImpl;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;
import ua.kpi.service.GuestService;

import java.util.Optional;

/**
 * Class for receiving and processing data necessary for the guest.
 * @author Vladyslav Avdiienko
 * @version 1.0
 */
public class GuestServiceImpl implements GuestService {

    private final UserDao userDao;
    private final GuestDao guestDao;

    {
        userDao = new UserDaoImpl();
        guestDao = new GuestDaoImpl();
    }

    /** Return user with given username.
     * @param username
     * @exception UserNotFoundException if user was not found.
     * @return User.
     * @see User
     */
    @Override
    public Optional<User> getUserByUsername(String username) throws UserNotFoundException {
        return Optional.ofNullable(userDao.getUserByUsername(username));
    }

    /** Create new user with given data.
     * @param user New user data to save.
     * @see UserDto
     * @return Creating result.
     */
    @Override
    public boolean createUser(UserDto user) {
        return guestDao.createUser(user.getBuilder()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .build());
    }

    /** Check if user with given login exists.
     * @param username
     * @return Checking result.
     */
    @Override
    public boolean isUserExistsWithUsername(String username) {
        return guestDao.isUserExistsWithUsername(username);
    }

    /** Check if the user exists with the given username and password.
     * @param username
     * @param password
     * @return Checking result.
     */
    @Override
    public boolean areUsernameAndPasswordCorrect(String username, String password) {
        return userDao.areUsernameAndPasswordCorrect(username, password);
    }
}
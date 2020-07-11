package ua.kpi.service.impl;

import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.dao.UserDao;
import ua.kpi.dao.impl.UserDaoImpl;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;
import ua.kpi.service.GuestService;

public class GuestServiceImpl implements GuestService {

    private final UserDao userDao;

    {
        userDao = new UserDaoImpl();
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        return userDao.getUserByUsername(username);
    }

    @Override
    public boolean createUser(UserDto user) {
        return userDao.createUser(user.getBuilder()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .build());
    }

    @Override
    public boolean isUserExistsWithUsername(String username) {
        return userDao.isUserExistsWithUsername(username);
    }

    @Override
    public boolean areUsernameAndPasswordCorrect(String username, String password) {
        return userDao.areUsernameAndPasswordCorrect(username, password);
    }
}
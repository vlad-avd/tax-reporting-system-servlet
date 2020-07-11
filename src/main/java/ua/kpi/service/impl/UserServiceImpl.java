package ua.kpi.service.impl;

import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.dao.UserDao;
import ua.kpi.dao.impl.UserDaoImpl;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;
import ua.kpi.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao;

    {
        userDao = new UserDaoImpl();
    }

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        return userDao.getUserById(id);
    }

    @Override
    public boolean updateUser(UserDto user) {
        return userDao.updateUser(user);
    }
}

package ua.kpi.service.impl;

import ua.kpi.dao.UserDao;
import ua.kpi.dao.impl.UserDaoImpl;
import ua.kpi.dto.UserDto;
import ua.kpi.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao;

    {
        userDao = new UserDaoImpl();
    }

    @Override
    public boolean updateUser(UserDto userDto) {
        return userDao.updateUser(userDto);
    }
}

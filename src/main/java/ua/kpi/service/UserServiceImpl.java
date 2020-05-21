package ua.kpi.service;

import ua.kpi.dao.UserDao;
import ua.kpi.dao.UserDaoImpl;
import ua.kpi.dto.UserDto;

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

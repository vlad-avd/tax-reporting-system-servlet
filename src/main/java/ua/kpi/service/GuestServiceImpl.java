package ua.kpi.service;

import ua.kpi.dao.UserDao;
import ua.kpi.dao.UserDaoImpl;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.Role;

public class GuestServiceImpl implements GuestService {

    private UserDao userDao;

    {
        userDao = new UserDaoImpl();
    }

    @Override
    public User getUserByUsername(String username) {
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
    public void setUserRole(Long id, Role role) {
        userDao.setUserRole(id, role);
    }
}
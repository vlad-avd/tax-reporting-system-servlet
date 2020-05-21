package ua.kpi.service;

import ua.kpi.dao.UserDao;
import ua.kpi.dao.UserDaoImpl;
import ua.kpi.db.ConnectionPool;
import ua.kpi.db.PGConnectionPool;
import ua.kpi.model.entity.User;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    //private ConnectionPool connectionPool = PGConnectionPool.getInstance();

    private UserDao userDao;

    {
        userDao = new UserDaoImpl();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}

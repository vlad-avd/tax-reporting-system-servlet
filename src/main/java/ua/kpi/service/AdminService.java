package ua.kpi.service;

import ua.kpi.model.entity.User;

import java.util.List;

public interface AdminService {
    User getUserById(Long id);

    List<User> getAllUsers();
}

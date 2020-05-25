package ua.kpi.service;

import ua.kpi.dto.StatisticsDto;
import ua.kpi.model.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {
    User getUserById(Long id);

    List<User> getAllUsers(int currentPage, int recordsPerPage);

    StatisticsDto getStatistics(Long userId);

    int getUsersNumber();
}

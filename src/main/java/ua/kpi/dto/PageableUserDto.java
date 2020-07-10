package ua.kpi.dto;

import ua.kpi.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class PageableUserDto {
    private List<User> userPage;
    private int rowNumber;

    public PageableUserDto() {
        userPage = new ArrayList<>();
    }

    public List<User> getUsers() {
        return userPage;
    }

    public void setUserPage(List<User> users) {
        this.userPage = users;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }
}

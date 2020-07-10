package ua.kpi.service.impl;

import ua.kpi.dao.UserDao;
import ua.kpi.dao.impl.UserDaoImpl;
import ua.kpi.dto.UserDto;
import ua.kpi.dto.UserValidationDto;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.Role;
import ua.kpi.service.GuestService;
import ua.kpi.util.UserValidator;

public class GuestServiceImpl implements GuestService {

    private final UserDao userDao;

    {
        userDao = new UserDaoImpl();
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public UserValidationDto createUser(UserDto user) {
        UserValidator userValidator = new UserValidator();

        UserValidationDto userValidationDto = new UserValidationDto();

        userValidationDto.setUserExistsWithUsername(userValidator.isUserExistsWithUsername(user.getUsername()));
        userValidationDto.setUsernameValid(userValidator.isValidUsername(user.getUsername()));
        userValidationDto.setPasswordValid(userValidator.isValidPassword(user.getPassword()));
        userValidationDto.setPasswordsMatch(userValidator.arePasswordsMatch(user.getPassword(), user.getPasswordConfirmation()));
        userValidationDto.setUserValid(!userValidationDto.isUserExistsWithUsername()
                && userValidationDto.isUsernameValid()
                && userValidationDto.isPasswordValid()
                && userValidationDto.arePasswordsMatch());

        if(userValidationDto.isUserValid()) {

            userDao.createUser(user.getBuilder()
                    .setUsername(user.getUsername())
                    .setPassword(user.getPassword())
                    .build());
        }

        return userValidationDto;
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
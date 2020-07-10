package ua.kpi.service.impl;

import ua.kpi.dao.UserDao;
import ua.kpi.dao.impl.UserDaoImpl;
import ua.kpi.dto.UserDto;
import ua.kpi.dto.UserValidationDto;
import ua.kpi.model.entity.User;
import ua.kpi.service.UserService;
import ua.kpi.util.UserValidator;

public class UserServiceImpl implements UserService {

    UserDao userDao;

    {
        userDao = new UserDaoImpl();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public UserValidationDto updateUser(UserDto user) {

        UserValidator userValidator = new UserValidator();

        UserValidationDto userValidationDto = new UserValidationDto();

        userValidationDto.setUsernameValid(userValidator.isValidUsername(user.getUsername()));
        userValidationDto.setPasswordValid(userValidator.isValidPassword(user.getPassword()));
        userValidationDto.setPasswordsMatch(userValidator.arePasswordsMatch(user.getPassword(), user.getPasswordConfirmation()));
        userValidationDto.setUserValid(userValidationDto.isUsernameValid()
                && userValidationDto.isPasswordValid()
                && userValidationDto.arePasswordsMatch());

        if(userValidationDto.isUserValid()) {

            userDao.updateUser(user);
        }

        return userValidationDto;
    }
}

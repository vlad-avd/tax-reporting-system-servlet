package ua.kpi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.dao.impl.UserDaoImpl;
import ua.kpi.dto.UserDto;
import ua.kpi.model.enums.Role;
import ua.kpi.service.impl.GuestServiceImpl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GuestServiceTest {
    @Mock
    private UserDaoImpl userDao;

    @InjectMocks
    private GuestServiceImpl guestService;

    private UserDto user;

    @Before
    public void init() {
        user = UserDto.newBuilder()
                .setUsername("user")
                .setPassword("pwd")
                .setRole(Role.ROLE_USER)
                .build();
    }

    @Test
    public void createUserTest() {
        boolean isUserCreated = guestService.createUser(user);

        Assert.assertTrue(isUserCreated);
        verify(userDao, times(1)).createUser(user);
    }

    @Test
    public void getUserByUsernameTest() throws UserNotFoundException {
//        Assert.assertEquals("pwd", guestService.getUserByUsername("user").getPassword());
//        Assert.assertEquals(Role.ROLE_USER, guestService.getUserByUsername("user").getRole());
    }
}

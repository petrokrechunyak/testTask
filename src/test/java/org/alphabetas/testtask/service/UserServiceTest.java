package org.alphabetas.testtask.service;

import org.alphabetas.testtask.controller.UserController;
import org.alphabetas.testtask.model.User;
import org.alphabetas.testtask.model.UserDto;
import org.alphabetas.testtask.repo.UserRepository;
import org.alphabetas.testtask.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserServiceTest {

    private UserService service;
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = mock(UserRepository.class);
        service = new UserServiceImpl(repository);
    }

    @Test
    public void shouldReturnTwoUsers_Test() {

        User user = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2005, 1, 1));
        User user2 = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2010, 1, 1));
        User user3 = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2015, 1, 1));
        User user4 = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2020, 1, 1));

        when(repository.findAll()).thenReturn(List.of(user, user3, user4, user2));

        List<User> users = service.findAllInRange("01-01-2009", "12-12-2019");

        int expectedSize = 2;
        Assertions.assertEquals(expectedSize, users.size());

    }

    @Test
    public void shouldReturnNothing_Test() {

        User user = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2005, 1, 1));
        User user2 = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2010, 1, 1));
        User user3 = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2015, 1, 1));
        User user4 = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2020, 1, 1));

        when(repository.findAll()).thenReturn(List.of(user, user3, user4, user2));

        List<User> users = service.findAllInRange("01-01-2022", "12-12-2024");

        int expectedSize = 0;
        Assertions.assertEquals(expectedSize, users.size());

    }

    @Test
    public void shouldReturnAll_Test() {

        User user = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2005, 1, 1));
        User user2 = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2010, 1, 1));
        User user3 = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2015, 1, 1));
        User user4 = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2020, 1, 1));

        when(repository.findAll()).thenReturn(List.of(user, user3, user4, user2));

        List<User> users = service.findAllInRange("01-01-2000", "12-12-2024");

        int expectedSize = 4;
        Assertions.assertEquals(expectedSize, users.size());

    }

    @Test
    public void shouldThrowError_fromBeforeTo_Test() {

        User user = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2005, 1, 1));
        User user2 = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2010, 1, 1));
        User user3 = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2015, 1, 1));
        User user4 = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2020, 1, 1));

        when(repository.findAll()).thenReturn(List.of(user, user3, user4, user2));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.findAllInRange("01-01-2020", "12-12-2010");
        });
    }

}

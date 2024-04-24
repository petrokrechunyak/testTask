package org.alphabetas.testtask.controller;

import org.alphabetas.testtask.model.User;
import org.alphabetas.testtask.model.UserDto;
import org.alphabetas.testtask.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @Test
    public void shouldSaveUser_Test()
            throws Exception {

        User user = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2000, 1, 1));

        when(service.save(user)).thenReturn(user);

        mvc.perform(post("/users/")
                        .content(("{\n" +
                                "    \"email\":\"%s\",\n" +
                                "    \"firstName\":\"%s\",\n" +
                                "    \"lastName\":\"%s\",\n" +
                                "    \"birthDate\":\"%s\"\n" +
                                "}").formatted(user.getEmail(), user.getFirstName(), user.getLastName(),
                                user.getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(user.getEmail())));

    }

    @Test
    public void shouldNotSave_LowAge_Test()
            throws Exception {

        User user = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2020, 1, 1));

        when(service.save(user)).thenReturn(user);

        mvc.perform(post("/users/")
                        .content(("{\n" +
                                "    \"email\":\"%s\",\n" +
                                "    \"firstName\":\"%s\",\n" +
                                "    \"lastName\":\"%s\",\n" +
                                "    \"birthDate\":\"%s\"\n" +
                                "}").formatted(user.getEmail(), user.getFirstName(), user.getLastName(),
                                user.getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.birthDate", is("Your age is too small!")));
    }

    @Test
    public void shouldNotSave_BadEmail_Test()
            throws Exception {

        User user = new User("somemailmail.com", "firstname", "lastname", LocalDate.of(2000, 1, 1));

        when(service.save(user)).thenReturn(user);

        mvc.perform(post("/users/")
                        .content(("{\n" +
                                "    \"email\":\"%s\",\n" +
                                "    \"firstName\":\"%s\",\n" +
                                "    \"lastName\":\"%s\",\n" +
                                "    \"birthDate\":\"%s\"\n" +
                                "}").formatted(user.getEmail(), user.getFirstName(), user.getLastName(),
                                user.getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email", is("Email format is wrong!")));
    }

    @Test
    public void shouldNotSave_EmptyFields_Test()
            throws Exception {

        User user = new User("somemailmail.com", "firstname", "lastname", LocalDate.of(2000, 1, 1));

        when(service.save(user)).thenReturn(user);

        mvc.perform(post("/users/")
                        .content(("{\n" +
                                "    \"email\":\"%s\",\n" +
                                "    \"birthDate\":\"%s\"\n" +
                                "}").formatted(user.getEmail(),
                                user.getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.firstName", is("First name cannot be blank!")))
                .andExpect(jsonPath("$.lastName", is("Last name cannot be blank!")));
    }

    @Test
    public void shouldDelete_Test()
            throws Exception {

        String email = "somemail@mail.com";

        doNothing().when(service).deleteById(email);

        mvc.perform(delete("/users/" + email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteById(email);
    }

    @Test
    public void shouldUpdateAllFields_Test()
            throws Exception {

        User user = new User("somemail@mail.com", "firstname", "lastname", LocalDate.of(2000, 1, 1));

        when(service.update(user)).thenReturn(user);

        mvc.perform(put("/users/")
                        .content(("{\n" +
                                "    \"email\":\"%s\",\n" +
                                "    \"firstName\":\"%s\",\n" +
                                "    \"lastName\":\"%s\",\n" +
                                "    \"birthDate\":\"%s\"\n" +
                                "}").formatted(user.getEmail(), user.getFirstName(), user.getLastName(),
                                user.getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).update(user);
    }



}

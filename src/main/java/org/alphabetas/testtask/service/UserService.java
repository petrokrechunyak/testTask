package org.alphabetas.testtask.service;

import org.alphabetas.testtask.model.User;
import org.alphabetas.testtask.model.UserDto;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

    List<User> findAllInRange(String from, String to);

    void clear();

    void deleteById(String id);

    User findById(String id);

    User update(UserDto dto);

    User update(User user);
}

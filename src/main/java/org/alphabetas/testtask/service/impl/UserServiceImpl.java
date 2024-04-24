package org.alphabetas.testtask.service.impl;

import org.alphabetas.testtask.mapper.UserMapper;
import org.alphabetas.testtask.model.User;
import org.alphabetas.testtask.model.UserDto;
import org.alphabetas.testtask.repo.UserRepository;
import org.alphabetas.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllInRange(String from, String to) {
        List<User> all = findAll();
        LocalDate fromDate = parseDate(from);
        LocalDate toDate = parseDate(to);

        if(fromDate.isAfter(toDate)) {
            throw new IllegalArgumentException("\"From\" date cannot be after \"to\" date!");
        }

        return all.stream().filter(user -> {
            LocalDate userDate = user.getBirthDate();
            return userDate.isAfter(fromDate) && userDate.isBefore(toDate);
        }).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public static LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Wrong date format: " + dateString);
        }
    }

    public User update(UserDto dto) {
        User user = findById(dto.getEmail());
        userMapper.updateUserFromDto(dto, user);
        return save(user);
    }

    public User update(User user) {
        return findById(user.getEmail());
    }

}
